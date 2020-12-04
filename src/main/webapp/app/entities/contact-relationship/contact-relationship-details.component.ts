import { Component, Vue, Inject } from 'vue-property-decorator';

import { IContactRelationship } from '@/shared/model/contact-relationship.model';
import ContactRelationshipService from './contact-relationship.service';

@Component
export default class ContactRelationshipDetails extends Vue {
  @Inject('contactRelationshipService') private contactRelationshipService: () => ContactRelationshipService;
  public contactRelationship: IContactRelationship = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.contactRelationshipId) {
        vm.retrieveContactRelationship(to.params.contactRelationshipId);
      }
    });
  }

  public retrieveContactRelationship(contactRelationshipId) {
    this.contactRelationshipService()
      .find(contactRelationshipId)
      .then(res => {
        this.contactRelationship = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
