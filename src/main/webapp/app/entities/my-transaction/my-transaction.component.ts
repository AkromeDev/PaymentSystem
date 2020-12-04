import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IMyTransaction } from '@/shared/model/my-transaction.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import MyTransactionService from './my-transaction.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class MyTransaction extends mixins(AlertMixin) {
  @Inject('myTransactionService') private myTransactionService: () => MyTransactionService;
  private removeId: number = null;

  public myTransactions: IMyTransaction[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllMyTransactions();
  }

  public clear(): void {
    this.retrieveAllMyTransactions();
  }

  public retrieveAllMyTransactions(): void {
    this.isFetching = true;

    this.myTransactionService()
      .retrieve()
      .then(
        res => {
          this.myTransactions = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IMyTransaction): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeMyTransaction(): void {
    this.myTransactionService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A MyTransaction is deleted with identifier ' + this.removeId;
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllMyTransactions();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
