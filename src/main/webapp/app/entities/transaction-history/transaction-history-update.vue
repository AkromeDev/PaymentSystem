<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="paymentSystemApp.transactionHistory.home.createOrEditLabel">Create or edit a TransactionHistory</h2>
                <div>
                    <div class="form-group" v-if="transactionHistory.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="transactionHistory.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="transaction-history-recipientMail">Recipient Mail</label>
                        <input type="text" class="form-control" name="recipientMail" id="transaction-history-recipientMail"
                            :class="{'valid': !$v.transactionHistory.recipientMail.$invalid, 'invalid': $v.transactionHistory.recipientMail.$invalid }" v-model="$v.transactionHistory.recipientMail.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="transaction-history-date">Date</label>
                        <div class="d-flex">
                            <input id="transaction-history-date" type="datetime-local" class="form-control" name="date" :class="{'valid': !$v.transactionHistory.date.$invalid, 'invalid': $v.transactionHistory.date.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.transactionHistory.date.$model)"
                            @change="updateInstantField('date', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="transaction-history-amount">Amount</label>
                        <input type="number" class="form-control" name="amount" id="transaction-history-amount"
                            :class="{'valid': !$v.transactionHistory.amount.$invalid, 'invalid': $v.transactionHistory.amount.$invalid }" v-model.number="$v.transactionHistory.amount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="transaction-history-description">Description</label>
                        <input type="text" class="form-control" name="description" id="transaction-history-description"
                            :class="{'valid': !$v.transactionHistory.description.$invalid, 'invalid': $v.transactionHistory.description.$invalid }" v-model="$v.transactionHistory.description.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="transaction-history-bankAccount">Bank Account</label>
                        <select class="form-control" id="transaction-history-bankAccount" name="bankAccount" v-model="transactionHistory.bankAccount">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="transactionHistory.bankAccount && bankAccountOption.id === transactionHistory.bankAccount.id ? transactionHistory.bankAccount : bankAccountOption" v-for="bankAccountOption in bankAccounts" :key="bankAccountOption.id">{{bankAccountOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="transaction-history-buddy">Buddy</label>
                        <select class="form-control" id="transaction-history-buddy" name="buddy" v-model="transactionHistory.buddy">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="transactionHistory.buddy && buddyOption.id === transactionHistory.buddy.id ? transactionHistory.buddy : buddyOption" v-for="buddyOption in buddies" :key="buddyOption.id">{{buddyOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.transactionHistory.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./transaction-history-update.component.ts">
</script>
