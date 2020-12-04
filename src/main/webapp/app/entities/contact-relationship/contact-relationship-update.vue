<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="paymentSystemApp.contactRelationship.home.createOrEditLabel">Create or edit a ContactRelationship</h2>
                <div>
                    <div class="form-group" v-if="contactRelationship.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="contactRelationship.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="contact-relationship-userId1">User Id 1</label>
                        <input type="number" class="form-control" name="userId1" id="contact-relationship-userId1"
                            :class="{'valid': !$v.contactRelationship.userId1.$invalid, 'invalid': $v.contactRelationship.userId1.$invalid }" v-model.number="$v.contactRelationship.userId1.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="contact-relationship-userId2">User Id 2</label>
                        <input type="number" class="form-control" name="userId2" id="contact-relationship-userId2"
                            :class="{'valid': !$v.contactRelationship.userId2.$invalid, 'invalid': $v.contactRelationship.userId2.$invalid }" v-model.number="$v.contactRelationship.userId2.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="contact-relationship-buddy">Buddy</label>
                        <select class="form-control" id="contact-relationship-buddy" name="buddy" v-model="contactRelationship.buddy">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="contactRelationship.buddy && buddyOption.id === contactRelationship.buddy.id ? contactRelationship.buddy : buddyOption" v-for="buddyOption in buddies" :key="buddyOption.id">{{buddyOption.id}}</option>
                        </select>
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
