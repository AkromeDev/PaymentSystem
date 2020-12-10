<template>
    <div>
        <h2 id="page-heading">
            <span id="bank-account-heading">Bank Accounts</span>
            <router-link :to="{name: 'BankAccountCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-bank-account">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Bank Account
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
        <div class="alert alert-warning" v-if="!isFetching && bankAccounts && bankAccounts.length === 0">
            <span>No bankAccounts found</span>
        </div>
        <div class="table-responsive" v-if="bankAccounts && bankAccounts.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span>Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('iban')"><span>Iban</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'iban'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('bic')"><span>Bic</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bic'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="bankAccount in bankAccounts"
                    :key="bankAccount.id">
                    <td>
                        <router-link :to="{name: 'BankAccountView', params: {bankAccountId: bankAccount.id}}">{{bankAccount.id}}</router-link>
                    </td>
                    <td>{{bankAccount.name}}</td>
                    <td>{{bankAccount.iban}}</td>
                    <td>{{bankAccount.bic}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'BankAccountView', params: {bankAccountId: bankAccount.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'BankAccountEdit', params: {bankAccountId: bankAccount.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(bankAccount)"
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
            <span slot="modal-title"><span id="paymentSystemApp.bankAccount.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-bankAccount-heading">Are you sure you want to delete this Bank Account?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-bankAccount" v-on:click="removeBankAccount()">Delete</button>
            </div>
        </b-modal>
        <div v-show="bankAccounts && bankAccounts.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./bank-account.component.ts">
</script>
