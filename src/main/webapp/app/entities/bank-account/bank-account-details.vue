<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="bankAccount">
                <h2 class="jh-entity-heading"><span>BankAccount</span> {{bankAccount.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Name</span>
                    </dt>
                    <dd>
                        <span>{{bankAccount.name}}</span>
                    </dd>
                    <dt>
                        <span>Iban</span>
                    </dt>
                    <dd>
                        <span>{{bankAccount.iban}}</span>
                    </dd>
                    <dt>
                        <span>Bic</span>
                    </dt>
                    <dd>
                        <span>{{bankAccount.bic}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="bankAccount.id" :to="{name: 'BankAccountEdit', params: {bankAccountId: bankAccount.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./bank-account-details.component.ts">
</script>
