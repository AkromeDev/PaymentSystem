<template>
    <div>
        <h2 id="page-heading">
            <span id="contact-relationship-heading">Contact Relationships</span>
            <router-link :to="{name: 'ContactRelationshipCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-contact-relationship">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Contact Relationship
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && contactRelationships && contactRelationships.length === 0">
            <span>No contactRelationships found</span>
        </div>
        <div class="table-responsive" v-if="contactRelationships && contactRelationships.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span>ID</span></th>
                    <th><span>User Id 1</span></th>
                    <th><span>User Id 2</span></th>
                    <th><span>Buddy</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="contactRelationship in contactRelationships"
                    :key="contactRelationship.id">
                    <td>
                        <router-link :to="{name: 'ContactRelationshipView', params: {contactRelationshipId: contactRelationship.id}}">{{contactRelationship.id}}</router-link>
                    </td>
                    <td>{{contactRelationship.userId1}}</td>
                    <td>{{contactRelationship.userId2}}</td>
                    <td>
                        <div v-if="contactRelationship.buddy">
                            <router-link :to="{name: 'BuddyView', params: {buddyId: contactRelationship.buddy.id}}">{{contactRelationship.buddy.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ContactRelationshipView', params: {contactRelationshipId: contactRelationship.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'ContactRelationshipEdit', params: {contactRelationshipId: contactRelationship.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(contactRelationship)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="paymentSystemApp.contactRelationship.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-contactRelationship-heading">Are you sure you want to delete this Contact Relationship?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-contactRelationship" v-on:click="removeContactRelationship()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./contact-relationship.component.ts">
</script>
