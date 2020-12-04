/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import TransactionHistoryComponent from '@/entities/transaction-history/transaction-history.vue';
import TransactionHistoryClass from '@/entities/transaction-history/transaction-history.component';
import TransactionHistoryService from '@/entities/transaction-history/transaction-history.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.component('jhi-sort-indicator', {});
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
  describe('TransactionHistory Management Component', () => {
    let wrapper: Wrapper<TransactionHistoryClass>;
    let comp: TransactionHistoryClass;
    let transactionHistoryServiceStub: SinonStubbedInstance<TransactionHistoryService>;

    beforeEach(() => {
      transactionHistoryServiceStub = sinon.createStubInstance<TransactionHistoryService>(TransactionHistoryService);
      transactionHistoryServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TransactionHistoryClass>(TransactionHistoryComponent, {
        store,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          transactionHistoryService: () => transactionHistoryServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      transactionHistoryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTransactionHistorys();
      await comp.$nextTick();

      // THEN
      expect(transactionHistoryServiceStub.retrieve.called).toBeTruthy();
      expect(comp.transactionHistories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      transactionHistoryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(transactionHistoryServiceStub.retrieve.called).toBeTruthy();
      expect(comp.transactionHistories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      transactionHistoryServiceStub.retrieve.reset();
      transactionHistoryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(transactionHistoryServiceStub.retrieve.callCount).toEqual(2);
      expect(comp.page).toEqual(1);
      expect(comp.transactionHistories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,asc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,asc', 'id']);
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      transactionHistoryServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeTransactionHistory();
      await comp.$nextTick();

      // THEN
      expect(transactionHistoryServiceStub.delete.called).toBeTruthy();
      expect(transactionHistoryServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
