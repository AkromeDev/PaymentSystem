<template>
    <div>
        <h2 id="page-heading">
            <span id="buddy-heading">Buddies</span>
            <router-link :to="{name: 'BuddyCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-buddy">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Buddy
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
        <div class="alert alert-warning" v-if="!isFetching && buddies && buddies.length === 0">
            <span>No buddies found</span>
        </div>
        <div class="table-responsive" v-if="buddies && buddies.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('firstName')"><span>First Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'firstName'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('lastName')"><span>Last Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastName'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('email')"><span>Email</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('phoneNumber')"><span>Phone Number</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneNumber'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('balance')"><span>Balance</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'balance'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('user.id')"><span>User</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'user.id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('bankAccount.id')"><span>Bank Account</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bankAccount.id'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="buddy in buddies"
                    :key="buddy.id">
                    <td>
                        <router-link :to="{name: 'BuddyView', params: {buddyId: buddy.id}}">{{buddy.id}}</router-link>
                    </td>
                    <td>{{buddy.firstName}}</td>
                    <td>{{buddy.lastName}}</td>
                    <td>{{buddy.email}}</td>
                    <td>{{buddy.phoneNumber}}</td>
                    <td>{{buddy.balance}}</td>
                    <td>
                        {{buddy.user ? buddy.user.id : ''}}
                    </td>
                    <td>
                        <div v-if="buddy.bankAccount">
                            <router-link :to="{name: 'BankAccountView', params: {bankAccountId: buddy.bankAccount.id}}">{{buddy.bankAccount.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'BuddyView', params: {buddyId: buddy.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'BuddyEdit', params: {buddyId: buddy.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(buddy)"
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
                <infinite-loading
                    ref="infiniteLoading"
                    v-if="totalItems > itemsPerPage"
                    :identifier="infiniteId"
                    slot="append"
                    @infinite="loadMore"
                    force-use-infinite-wrapper=".el-table__body-wrapper"
                    :distance='20'>
                </infinite-loading>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="paymentSystemApp.buddy.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-buddy-heading">Are you sure you want to delete this Buddy?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-buddy" v-on:click="removeBuddy()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./buddy.component.ts">
</script>
