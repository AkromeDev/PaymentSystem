import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IBankAccount } from '@/shared/model/bank-account.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import BankAccountService from './bank-account.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class BankAccount extends mixins(AlertMixin) {
  @Inject('bankAccountService') private bankAccountService: () => BankAccountService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public bankAccounts: IBankAccount[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllBankAccounts();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllBankAccounts();
  }

  public retrieveAllBankAccounts(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.bankAccountService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.bankAccounts = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IBankAccount): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeBankAccount(): void {
    this.bankAccountService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A BankAccount is deleted with identifier ' + this.removeId;
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllBankAccounts();
        this.closeDialog();
      });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllBankAccounts();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
