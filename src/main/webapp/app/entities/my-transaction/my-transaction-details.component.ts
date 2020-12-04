import { Component, Vue, Inject } from 'vue-property-decorator';

import { IMyTransaction } from '@/shared/model/my-transaction.model';
import MyTransactionService from './my-transaction.service';

@Component
export default class MyTransactionDetails extends Vue {
  @Inject('myTransactionService') private myTransactionService: () => MyTransactionService;
  public myTransaction: IMyTransaction = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.myTransactionId) {
        vm.retrieveMyTransaction(to.params.myTransactionId);
      }
    });
  }

  public retrieveMyTransaction(myTransactionId) {
    this.myTransactionService()
      .find(myTransactionId)
      .then(res => {
        this.myTransaction = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
