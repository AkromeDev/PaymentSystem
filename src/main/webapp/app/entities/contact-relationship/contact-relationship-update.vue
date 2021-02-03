<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="paymentSystemApp.contactRelationship.home.createOrEditLabel">Create or edit a ContactRelationship</h2>
                <div>
                    <div class="form-group">
                        <label class="form-control-label" for="contact-relationship-email">Email</label>
                        <input type="text" class="form-control" name="email" id="contact-relationship-email"
                            :class="{'valid': !$v.contactRelationship.email.$invalid, 'invalid': $v.contactRelationship.email.$invalid }" v-model="$v.contactRelationship.email.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.contactRelationship.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./contact-relationship-update.component.ts">
</script>
