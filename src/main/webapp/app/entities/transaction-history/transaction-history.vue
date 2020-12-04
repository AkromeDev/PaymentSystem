<template>
    <div>
        <h2 id="page-heading">
            <span id="transaction-history-heading">Transaction Histories</span>
            <router-link :to="{name: 'TransactionHistoryCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-transaction-history">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Transaction History
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
        <div class="alert alert-warning" v-if="!isFetching && transactionHistories && transactionHistories.length === 0">
            <span>No transactionHistories found</span>
        </div>
        <div class="table-responsive" v-if="transactionHistories && transactionHistories.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('recipientMail')"><span>Recipient Mail</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'recipientMail'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('date')"><span>Date</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'date'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('amount')"><span>Amount</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('description')"><span>Description</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('bankAccount.id')"><span>Bank Account</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bankAccount.id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('buddy.id')"><span>Buddy</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'buddy.id'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="transactionHistory in transactionHistories"
                    :key="transactionHistory.id">
                    <td>
                        <router-link :to="{name: 'TransactionHistoryView', params: {transactionHistoryId: transactionHistory.id}}">{{transactionHistory.id}}</router-link>
                    </td>
                    <td>{{transactionHistory.recipientMail}}</td>
                    <td>{{transactionHistory.date | formatDate}}</td>
                    <td>{{transactionHistory.amount}}</td>
                    <td>{{transactionHistory.description}}</td>
                    <td>
                        <div v-if="transactionHistory.bankAccount">
                            <router-link :to="{name: 'BankAccountView', params: {bankAccountId: transactionHistory.bankAccount.id}}">{{transactionHistory.bankAccount.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="transactionHistory.buddy">
                            <router-link :to="{name: 'BuddyView', params: {buddyId: transactionHistory.buddy.id}}">{{transactionHistory.buddy.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'TransactionHistoryView', params: {transactionHistoryId: transactionHistory.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'TransactionHistoryEdit', params: {transactionHistoryId: transactionHistory.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(transactionHistory)"
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
            <span slot="modal-title"><span id="paymentSystemApp.transactionHistory.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-transactionHistory-heading">Are you sure you want to delete this Transaction History?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-transactionHistory" v-on:click="removeTransactionHistory()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./transaction-history.component.ts">
</script>
