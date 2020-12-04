import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import TransactionHistoryService from '../transaction-history/transaction-history.service';
import { ITransactionHistory } from '@/shared/model/transaction-history.model';

import AlertService from '@/shared/alert/alert.service';
import { IMyTransaction, MyTransaction } from '@/shared/model/my-transaction.model';
import MyTransactionService from './my-transaction.service';

const validations: any = {
  myTransaction: {
    email: {},
    amount: {},
  },
};

@Component({
  validations,
})
export default class MyTransactionUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('myTransactionService') private myTransactionService: () => MyTransactionService;
  public myTransaction: IMyTransaction = new MyTransaction();

  @Inject('transactionHistoryService') private transactionHistoryService: () => TransactionHistoryService;

  public transactionHistories: ITransactionHistory[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.myTransactionId) {
        vm.retrieveMyTransaction(to.params.myTransactionId);
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
    this.myTransaction.transactionHistories = [];
  }

  public save(): void {
    this.isSaving = true;
    if (this.myTransaction.id) {
      this.myTransactionService()
        .update(this.myTransaction)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A MyTransaction is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.myTransactionService()
        .create(this.myTransaction)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A MyTransaction is created with identifier ' + param.id;
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveMyTransaction(myTransactionId): void {
    this.myTransactionService()
      .find(myTransactionId)
      .then(res => {
        this.myTransaction = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.transactionHistoryService()
      .retrieve()
      .then(res => {
        this.transactionHistories = res.data;
      });
  }

  public getSelected(selectedVals, option): any {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
