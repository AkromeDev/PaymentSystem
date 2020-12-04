import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import BuddyService from '../buddy/buddy.service';
import { IBuddy } from '@/shared/model/buddy.model';

import AlertService from '@/shared/alert/alert.service';
import { IContactRelationship, ContactRelationship } from '@/shared/model/contact-relationship.model';
import ContactRelationshipService from './contact-relationship.service';

const validations: any = {
  contactRelationship: {
    userId1: {},
    userId2: {},
  },
};

@Component({
  validations,
})
export default class ContactRelationshipUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('contactRelationshipService') private contactRelationshipService: () => ContactRelationshipService;
  public contactRelationship: IContactRelationship = new ContactRelationship();

  @Inject('buddyService') private buddyService: () => BuddyService;

  public buddies: IBuddy[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.contactRelationshipId) {
        vm.retrieveContactRelationship(to.params.contactRelationshipId);
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
    if (this.contactRelationship.id) {
      this.contactRelationshipService()
        .update(this.contactRelationship)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A ContactRelationship is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.contactRelationshipService()
        .create(this.contactRelationship)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A ContactRelationship is created with identifier ' + param.id;
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveContactRelationship(contactRelationshipId): void {
    this.contactRelationshipService()
      .find(contactRelationshipId)
      .then(res => {
        this.contactRelationship = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.buddyService()
      .retrieve()
      .then(res => {
        this.buddies = res.data;
      });
  }
}
