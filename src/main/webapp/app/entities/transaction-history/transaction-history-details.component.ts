import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITransactionHistory } from '@/shared/model/transaction-history.model';
import TransactionHistoryService from './transaction-history.service';

@Component
export default class TransactionHistoryDetails extends Vue {
  @Inject('transactionHistoryService') private transactionHistoryService: () => TransactionHistoryService;
  public transactionHistory: ITransactionHistory = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.transactionHistoryId) {
        vm.retrieveTransactionHistory(to.params.transactionHistoryId);
      }
    });
  }

  public retrieveTransactionHistory(transactionHistoryId) {
    this.transactionHistoryService()
      .find(transactionHistoryId)
      .then(res => {
        this.transactionHistory = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
