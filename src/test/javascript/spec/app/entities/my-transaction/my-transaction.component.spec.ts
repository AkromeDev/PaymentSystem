/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import MyTransactionComponent from '@/entities/my-transaction/my-transaction.vue';
import MyTransactionClass from '@/entities/my-transaction/my-transaction.component';
import MyTransactionService from '@/entities/my-transaction/my-transaction.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('MyTransaction Management Component', () => {
    let wrapper: Wrapper<MyTransactionClass>;
    let comp: MyTransactionClass;
    let myTransactionServiceStub: SinonStubbedInstance<MyTransactionService>;

    beforeEach(() => {
      myTransactionServiceStub = sinon.createStubInstance<MyTransactionService>(MyTransactionService);
      myTransactionServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<MyTransactionClass>(MyTransactionComponent, {
        store,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          myTransactionService: () => myTransactionServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      myTransactionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllMyTransactions();
      await comp.$nextTick();

      // THEN
      expect(myTransactionServiceStub.retrieve.called).toBeTruthy();
      expect(comp.myTransactions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      myTransactionServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeMyTransaction();
      await comp.$nextTick();

      // THEN
      expect(myTransactionServiceStub.delete.called).toBeTruthy();
      expect(myTransactionServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
