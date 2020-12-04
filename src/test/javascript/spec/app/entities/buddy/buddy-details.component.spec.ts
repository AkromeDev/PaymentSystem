/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import BuddyDetailComponent from '@/entities/buddy/buddy-details.vue';
import BuddyClass from '@/entities/buddy/buddy-details.component';
import BuddyService from '@/entities/buddy/buddy.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Buddy Management Detail Component', () => {
    let wrapper: Wrapper<BuddyClass>;
    let comp: BuddyClass;
    let buddyServiceStub: SinonStubbedInstance<BuddyService>;

    beforeEach(() => {
      buddyServiceStub = sinon.createStubInstance<BuddyService>(BuddyService);

      wrapper = shallowMount<BuddyClass>(BuddyDetailComponent, { store, localVue, provide: { buddyService: () => buddyServiceStub } });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundBuddy = { id: 123 };
        buddyServiceStub.find.resolves(foundBuddy);

        // WHEN
        comp.retrieveBuddy(123);
        await comp.$nextTick();

        // THEN
        expect(comp.buddy).toBe(foundBuddy);
      });
    });
  });
});
