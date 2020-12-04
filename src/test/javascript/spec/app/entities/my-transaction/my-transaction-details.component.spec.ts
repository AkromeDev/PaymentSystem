/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import MyTransactionDetailComponent from '@/entities/my-transaction/my-transaction-details.vue';
import MyTransactionClass from '@/entities/my-transaction/my-transaction-details.component';
import MyTransactionService from '@/entities/my-transaction/my-transaction.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('MyTransaction Management Detail Component', () => {
    let wrapper: Wrapper<MyTransactionClass>;
    let comp: MyTransactionClass;
    let myTransactionServiceStub: SinonStubbedInstance<MyTransactionService>;

    beforeEach(() => {
      myTransactionServiceStub = sinon.createStubInstance<MyTransactionService>(MyTransactionService);

      wrapper = shallowMount<MyTransactionClass>(MyTransactionDetailComponent, {
        store,
        localVue,
        provide: { myTransactionService: () => myTransactionServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundMyTransaction = { id: 123 };
        myTransactionServiceStub.find.resolves(foundMyTransaction);

        // WHEN
        comp.retrieveMyTransaction(123);
        await comp.$nextTick();

        // THEN
        expect(comp.myTransaction).toBe(foundMyTransaction);
      });
    });
  });
});
