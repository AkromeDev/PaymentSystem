/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ContactRelationshipDetailComponent from '@/entities/contact-relationship/contact-relationship-details.vue';
import ContactRelationshipClass from '@/entities/contact-relationship/contact-relationship-details.component';
import ContactRelationshipService from '@/entities/contact-relationship/contact-relationship.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ContactRelationship Management Detail Component', () => {
    let wrapper: Wrapper<ContactRelationshipClass>;
    let comp: ContactRelationshipClass;
    let contactRelationshipServiceStub: SinonStubbedInstance<ContactRelationshipService>;

    beforeEach(() => {
      contactRelationshipServiceStub = sinon.createStubInstance<ContactRelationshipService>(ContactRelationshipService);

      wrapper = shallowMount<ContactRelationshipClass>(ContactRelationshipDetailComponent, {
        store,
        localVue,
        provide: { contactRelationshipService: () => contactRelationshipServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundContactRelationship = { id: 123 };
        contactRelationshipServiceStub.find.resolves(foundContactRelationship);

        // WHEN
        comp.retrieveContactRelationship(123);
        await comp.$nextTick();

        // THEN
        expect(comp.contactRelationship).toBe(foundContactRelationship);
      });
    });
  });
});
