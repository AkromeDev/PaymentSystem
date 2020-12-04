/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ContactRelationshipComponent from '@/entities/contact-relationship/contact-relationship.vue';
import ContactRelationshipClass from '@/entities/contact-relationship/contact-relationship.component';
import ContactRelationshipService from '@/entities/contact-relationship/contact-relationship.service';

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
  describe('ContactRelationship Management Component', () => {
    let wrapper: Wrapper<ContactRelationshipClass>;
    let comp: ContactRelationshipClass;
    let contactRelationshipServiceStub: SinonStubbedInstance<ContactRelationshipService>;

    beforeEach(() => {
      contactRelationshipServiceStub = sinon.createStubInstance<ContactRelationshipService>(ContactRelationshipService);
      contactRelationshipServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ContactRelationshipClass>(ContactRelationshipComponent, {
        store,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          contactRelationshipService: () => contactRelationshipServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      contactRelationshipServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllContactRelationships();
      await comp.$nextTick();

      // THEN
      expect(contactRelationshipServiceStub.retrieve.called).toBeTruthy();
      expect(comp.contactRelationships[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      contactRelationshipServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeContactRelationship();
      await comp.$nextTick();

      // THEN
      expect(contactRelationshipServiceStub.delete.called).toBeTruthy();
      expect(contactRelationshipServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
