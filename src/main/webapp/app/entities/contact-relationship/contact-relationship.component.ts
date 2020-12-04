import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IContactRelationship } from '@/shared/model/contact-relationship.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import ContactRelationshipService from './contact-relationship.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ContactRelationship extends mixins(AlertMixin) {
  @Inject('contactRelationshipService') private contactRelationshipService: () => ContactRelationshipService;
  private removeId: number = null;

  public contactRelationships: IContactRelationship[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllContactRelationships();
  }

  public clear(): void {
    this.retrieveAllContactRelationships();
  }

  public retrieveAllContactRelationships(): void {
    this.isFetching = true;

    this.contactRelationshipService()
      .retrieve()
      .then(
        res => {
          this.contactRelationships = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IContactRelationship): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeContactRelationship(): void {
    this.contactRelationshipService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A ContactRelationship is deleted with identifier ' + this.removeId;
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllContactRelationships();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
