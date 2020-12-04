<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="transactionHistory">
                <h2 class="jh-entity-heading"><span>TransactionHistory</span> {{transactionHistory.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Recipient Mail</span>
                    </dt>
                    <dd>
                        <span>{{transactionHistory.recipientMail}}</span>
                    </dd>
                    <dt>
                        <span>Date</span>
                    </dt>
                    <dd>
                        <span>{{transactionHistory.date | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Amount</span>
                    </dt>
                    <dd>
                        <span>{{transactionHistory.amount}}</span>
                    </dd>
                    <dt>
                        <span>Description</span>
                    </dt>
                    <dd>
                        <span>{{transactionHistory.description}}</span>
                    </dd>
                    <dt>
                        <span>Bank Account</span>
                    </dt>
                    <dd>
                        <div v-if="transactionHistory.bankAccount">
                            <router-link :to="{name: 'BankAccountView', params: {bankAccountId: transactionHistory.bankAccount.id}}">{{transactionHistory.bankAccount.id}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span>Buddy</span>
                    </dt>
                    <dd>
                        <div v-if="transactionHistory.buddy">
                            <router-link :to="{name: 'BuddyView', params: {buddyId: transactionHistory.buddy.id}}">{{transactionHistory.buddy.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="transactionHistory.id" :to="{name: 'TransactionHistoryEdit', params: {transactionHistoryId: transactionHistory.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./transaction-history-details.component.ts">
</script>
