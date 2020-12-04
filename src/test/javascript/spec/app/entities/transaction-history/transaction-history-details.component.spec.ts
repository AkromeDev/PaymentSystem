/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import TransactionHistoryDetailComponent from '@/entities/transaction-history/transaction-history-details.vue';
import TransactionHistoryClass from '@/entities/transaction-history/transaction-history-details.component';
import TransactionHistoryService from '@/entities/transaction-history/transaction-history.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('TransactionHistory Management Detail Component', () => {
    let wrapper: Wrapper<TransactionHistoryClass>;
    let comp: TransactionHistoryClass;
    let transactionHistoryServiceStub: SinonStubbedInstance<TransactionHistoryService>;

    beforeEach(() => {
      transactionHistoryServiceStub = sinon.createStubInstance<TransactionHistoryService>(TransactionHistoryService);

      wrapper = shallowMount<TransactionHistoryClass>(TransactionHistoryDetailComponent, {
        store,
        localVue,
        provide: { transactionHistoryService: () => transactionHistoryServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTransactionHistory = { id: 123 };
        transactionHistoryServiceStub.find.resolves(foundTransactionHistory);

        // WHEN
        comp.retrieveTransactionHistory(123);
        await comp.$nextTick();

        // THEN
        expect(comp.transactionHistory).toBe(foundTransactionHistory);
      });
    });
  });
});
