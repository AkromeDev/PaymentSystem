import { Component, Vue, Inject } from 'vue-property-decorator';

import { IBuddy } from '@/shared/model/buddy.model';
import BuddyService from './buddy.service';

@Component
export default class BuddyActive extends Vue {
  @Inject('buddyService') private buddyService: () => BuddyService;
  public buddy: IBuddy = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
        vm.getBuddy();
        console.log("This is being called XX");
    });
  }

  public getBuddy() {
    console.log("This is being called ZZ");
    this.buddyService()
      .get()
      .then(res => {
        this.buddy = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
