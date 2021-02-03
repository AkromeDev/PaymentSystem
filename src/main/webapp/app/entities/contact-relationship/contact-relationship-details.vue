<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="contactRelationship">
                <h2 class="jh-entity-heading"><span>Contact: </span> {{contactRelationship.name}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Email</span>
                    </dt>
                    <dd>
                        <span>{{contactRelationship.email}}</span>
                    </dd>
                    <dt>
                        <span>name</span>
                    </dt>
                    <dd>
                        <span>{{contactRelationship.name}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="contactRelationship.id" :to="{name: 'ContactRelationshipEdit', params: {contactRelationshipId: contactRelationship.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./contact-relationship-details.component.ts">
</script>
