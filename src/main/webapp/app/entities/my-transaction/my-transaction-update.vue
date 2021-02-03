<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <!-- TODO Try to import the necessary code to activate the alert on this page => GOTO my-transaction-uptdate.component.ts -->
            <!-- <b-alert :show="dismissCountDown"
                    dismissible
                    :variant="alertType"
                    @dismissed="dismissCountDown=0"
                    @dismiss-count-down="countDownChanged">
                    {{alertMessage}}
            </b-alert> -->
            <br/>
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="paymentSystemApp.myTransaction.home.createOrEditLabel">Send Money to a contact  </h2>
                <div>
                    <div class="form-group" v-if="myTransaction.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="myTransaction.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="my-transaction-email">Email</label>
                        <input type="text" class="form-control" name="email" id="my-transaction-email"
                            :class="{'valid': !$v.myTransaction.email.$invalid, 'invalid': $v.myTransaction.email.$invalid }" v-model="$v.myTransaction.email.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="my-transaction-amount">Amount</label>
                        <input type="number" class="form-control" name="amount" id="my-transaction-amount"
                            :class="{'valid': !$v.myTransaction.amount.$invalid, 'invalid': $v.myTransaction.amount.$invalid }" v-model.number="$v.myTransaction.amount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="my-transaction-description">Description</label>
                        <input type="text" class="form-control" name="description" id="my-transaction-description"
                            :class="{'valid': !$v.myTransaction.description.$invalid, 'invalid': $v.myTransaction.description.$invalid }" v-model.number="$v.myTransaction.description.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.myTransaction.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./my-transaction-update.component.ts">
</script>
