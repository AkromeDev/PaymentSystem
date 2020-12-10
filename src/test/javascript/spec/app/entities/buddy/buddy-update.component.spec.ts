/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import BuddyUpdateComponent from '@/entities/buddy/buddy-update.vue';
import BuddyClass from '@/entities/buddy/buddy-update.component';
import BuddyService from '@/entities/buddy/buddy.service';

import UserService from '@/admin/user-management/user-management.service';

import BankAccountService from '@/entities/bank-account/bank-account.service';

import ContactRelationshipService from '@/entities/contact-relationship/contact-relationship.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Buddy Management Update Component', () => {
    let wrapper: Wrapper<BuddyClass>;
    let comp: BuddyClass;
    let buddyServiceStub: SinonStubbedInstance<BuddyService>;

    beforeEach(() => {
      buddyServiceStub = sinon.createStubInstance<BuddyService>(BuddyService);

      wrapper = shallowMount<BuddyClass>(BuddyUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          buddyService: () => buddyServiceStub,

          userService: () => new UserService(),

          bankAccountService: () => new BankAccountService(),

          contactRelationshipService: () => new ContactRelationshipService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.buddy = entity;
        buddyServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(buddyServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.buddy = entity;
        buddyServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(buddyServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
