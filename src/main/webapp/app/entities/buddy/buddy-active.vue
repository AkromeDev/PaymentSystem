<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="buddy">
                <h2 class="jh-entity-heading"><span>Buddy</span> {{buddy.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>First Name</span>
                    </dt>
                    <dd>
                        <span>{{buddy.firstName}}</span>
                    </dd>
                    <dt>
                        <span>Last Name</span>
                    </dt>
                    <dd>
                        <span>{{buddy.lastName}}</span>
                    </dd>
                    <dt>
                        <span>Email</span>
                    </dt>
                    <dd>
                        <span>{{buddy.email}}</span>
                    </dd>
                    <dt>
                        <span>Balance</span>
                    </dt>
                    <dd>
                        <span>{{buddy.balance}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="buddy.id" :to="{name: 'BuddyEdit', params: {buddyId: buddy.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
                <router-link v-else :to="{name: 'BuddyCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-buddy">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./buddy-active.component.ts">
</script>
