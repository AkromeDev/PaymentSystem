/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import MyTransactionUpdateComponent from '@/entities/my-transaction/my-transaction-update.vue';
import MyTransactionClass from '@/entities/my-transaction/my-transaction-update.component';
import MyTransactionService from '@/entities/my-transaction/my-transaction.service';

import TransactionHistoryService from '@/entities/transaction-history/transaction-history.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('MyTransaction Management Update Component', () => {
    let wrapper: Wrapper<MyTransactionClass>;
    let comp: MyTransactionClass;
    let myTransactionServiceStub: SinonStubbedInstance<MyTransactionService>;

    beforeEach(() => {
      myTransactionServiceStub = sinon.createStubInstance<MyTransactionService>(MyTransactionService);

      wrapper = shallowMount<MyTransactionClass>(MyTransactionUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          myTransactionService: () => myTransactionServiceStub,

          transactionHistoryService: () => new TransactionHistoryService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.myTransaction = entity;
        myTransactionServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(myTransactionServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.myTransaction = entity;
        myTransactionServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(myTransactionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
