/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import format from 'date-fns/format';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import TransactionHistoryUpdateComponent from '@/entities/transaction-history/transaction-history-update.vue';
import TransactionHistoryClass from '@/entities/transaction-history/transaction-history-update.component';
import TransactionHistoryService from '@/entities/transaction-history/transaction-history.service';

import BankAccountService from '@/entities/bank-account/bank-account.service';

import BuddyService from '@/entities/buddy/buddy.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('TransactionHistory Management Update Component', () => {
    let wrapper: Wrapper<TransactionHistoryClass>;
    let comp: TransactionHistoryClass;
    let transactionHistoryServiceStub: SinonStubbedInstance<TransactionHistoryService>;

    beforeEach(() => {
      transactionHistoryServiceStub = sinon.createStubInstance<TransactionHistoryService>(TransactionHistoryService);

      wrapper = shallowMount<TransactionHistoryClass>(TransactionHistoryUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          transactionHistoryService: () => transactionHistoryServiceStub,

          bankAccountService: () => new BankAccountService(),

          buddyService: () => new BuddyService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('load', () => {
      it('Should convert date from string', () => {
        // GIVEN
        const date = new Date('2019-10-15T11:42:02Z');

        // WHEN
        const convertedDate = comp.convertDateTimeFromServer(date);

        // THEN
        expect(convertedDate).toEqual(format(date, DATE_TIME_LONG_FORMAT));
      });

      it('Should not convert date if date is not present', () => {
        expect(comp.convertDateTimeFromServer(null)).toBeNull();
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.transactionHistory = entity;
        transactionHistoryServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(transactionHistoryServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.transactionHistory = entity;
        transactionHistoryServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(transactionHistoryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
