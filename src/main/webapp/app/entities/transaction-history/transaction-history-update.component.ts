import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import BankAccountService from '../bank-account/bank-account.service';
import { IBankAccount } from '@/shared/model/bank-account.model';

import BuddyService from '../buddy/buddy.service';
import { IBuddy } from '@/shared/model/buddy.model';

import MyTransactionService from '../my-transaction/my-transaction.service';
import { IMyTransaction } from '@/shared/model/my-transaction.model';

import AlertService from '@/shared/alert/alert.service';
import { ITransactionHistory, TransactionHistory } from '@/shared/model/transaction-history.model';
import TransactionHistoryService from './transaction-history.service';

const validations: any = {
  transactionHistory: {
    recipientMail: {},
    date: {},
    amount: {},
    description: {},
  },
};

@Component({
  validations,
})
export default class TransactionHistoryUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('transactionHistoryService') private transactionHistoryService: () => TransactionHistoryService;
  public transactionHistory: ITransactionHistory = new TransactionHistory();

  @Inject('bankAccountService') private bankAccountService: () => BankAccountService;

  public bankAccounts: IBankAccount[] = [];

  @Inject('buddyService') private buddyService: () => BuddyService;

  public buddies: IBuddy[] = [];

  @Inject('myTransactionService') private myTransactionService: () => MyTransactionService;

  public myTransactions: IMyTransaction[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.transactionHistoryId) {
        vm.retrieveTransactionHistory(to.params.transactionHistoryId);
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
    if (this.transactionHistory.id) {
      this.transactionHistoryService()
        .update(this.transactionHistory)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A TransactionHistory is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.transactionHistoryService()
        .create(this.transactionHistory)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A TransactionHistory is created with identifier ' + param.id;
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date) {
      return format(date, DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.transactionHistory[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.transactionHistory[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.transactionHistory[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.transactionHistory[field] = null;
    }
  }

  public retrieveTransactionHistory(transactionHistoryId): void {
    this.transactionHistoryService()
      .find(transactionHistoryId)
      .then(res => {
        res.date = new Date(res.date);
        this.transactionHistory = res;
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
    this.buddyService()
      .retrieve()
      .then(res => {
        this.buddies = res.data;
      });
    this.myTransactionService()
      .retrieve()
      .then(res => {
        this.myTransactions = res.data;
      });
  }
}
