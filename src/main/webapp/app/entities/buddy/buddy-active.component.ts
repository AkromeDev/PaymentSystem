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
    });
  }

  public getBuddy() {
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
