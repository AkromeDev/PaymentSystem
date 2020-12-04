<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="myTransaction">
                <h2 class="jh-entity-heading"><span>MyTransaction</span> {{myTransaction.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Email</span>
                    </dt>
                    <dd>
                        <span>{{myTransaction.email}}</span>
                    </dd>
                    <dt>
                        <span>Amount</span>
                    </dt>
                    <dd>
                        <span>{{myTransaction.amount}}</span>
                    </dd>
                    <dt>
                        <span>Transaction History</span>
                    </dt>
                    <dd>
                        <span v-for="(transactionHistory, i) in myTransaction.transactionHistories" :key="transactionHistory.id">{{i > 0 ? ', ' : ''}}
                            <router-link :to="{name: 'TransactionHistoryView', params: {transactionHistoryId: transactionHistory.id}}">{{transactionHistory.id}}</router-link>
                        </span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="myTransaction.id" :to="{name: 'MyTransactionEdit', params: {myTransactionId: myTransaction.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./my-transaction-details.component.ts">
</script>
