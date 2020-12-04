import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import BankAccountService from '../bank-account/bank-account.service';
import { IBankAccount } from '@/shared/model/bank-account.model';

import ContactRelationshipService from '../contact-relationship/contact-relationship.service';
import { IContactRelationship } from '@/shared/model/contact-relationship.model';

import AlertService from '@/shared/alert/alert.service';
import { IBuddy, Buddy } from '@/shared/model/buddy.model';
import BuddyService from './buddy.service';

const validations: any = {
  buddy: {
    firstName: {
      required,
    },
    lastName: {
      required,
    },
    email: {
      required,
    },
    phoneNumber: {},
    balance: {},
  },
};

@Component({
  validations,
})
export default class BuddyUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('buddyService') private buddyService: () => BuddyService;
  public buddy: IBuddy = new Buddy();

  @Inject('bankAccountService') private bankAccountService: () => BankAccountService;

  public bankAccounts: IBankAccount[] = [];

  @Inject('contactRelationshipService') private contactRelationshipService: () => ContactRelationshipService;

  public contactRelationships: IContactRelationship[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.buddyId) {
        vm.retrieveBuddy(to.params.buddyId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.buddy.id) {
      this.buddyService()
        .update(this.buddy)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Buddy is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.buddyService()
        .create(this.buddy)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Buddy is created with identifier ' + param.id;
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveBuddy(buddyId): void {
    this.buddyService()
      .find(buddyId)
      .then(res => {
        this.buddy = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.bankAccountService()
      .retrieve()
      .then(res => {
        this.bankAccounts = res.data;
      });
    this.contactRelationshipService()
      .retrieve()
      .then(res => {
        this.contactRelationships = res.data;
      });
  }
}
