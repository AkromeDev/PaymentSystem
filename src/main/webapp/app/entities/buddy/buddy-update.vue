<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="paymentSystemApp.buddy.home.createOrEditLabel">Create or edit a Buddy</h2>
                <div>
                    <div class="form-group" v-if="buddy.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="buddy.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="buddy-firstName">First Name</label>
                        <input type="text" class="form-control" name="firstName" id="buddy-firstName"
                            :class="{'valid': !$v.buddy.firstName.$invalid, 'invalid': $v.buddy.firstName.$invalid }" v-model="$v.buddy.firstName.$model"  required/>
                        <div v-if="$v.buddy.firstName.$anyDirty && $v.buddy.firstName.$invalid">
                            <small class="form-text text-danger" v-if="!$v.buddy.firstName.required">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="buddy-lastName">Last Name</label>
                        <input type="text" class="form-control" name="lastName" id="buddy-lastName"
                            :class="{'valid': !$v.buddy.lastName.$invalid, 'invalid': $v.buddy.lastName.$invalid }" v-model="$v.buddy.lastName.$model"  required/>
                        <div v-if="$v.buddy.lastName.$anyDirty && $v.buddy.lastName.$invalid">
                            <small class="form-text text-danger" v-if="!$v.buddy.lastName.required">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="buddy-email">Email</label>
                        <input type="text" class="form-control" name="email" id="buddy-email"
                            :class="{'valid': !$v.buddy.email.$invalid, 'invalid': $v.buddy.email.$invalid }" v-model="$v.buddy.email.$model"  required/>
                        <div v-if="$v.buddy.email.$anyDirty && $v.buddy.email.$invalid">
                            <small class="form-text text-danger" v-if="!$v.buddy.email.required">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="buddy-phoneNumber">Phone Number</label>
                        <input type="number" class="form-control" name="phoneNumber" id="buddy-phoneNumber"
                            :class="{'valid': !$v.buddy.phoneNumber.$invalid, 'invalid': $v.buddy.phoneNumber.$invalid }" v-model.number="$v.buddy.phoneNumber.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="buddy-balance">Balance</label>
                        <input type="number" class="form-control" name="balance" id="buddy-balance"
                            :class="{'valid': !$v.buddy.balance.$invalid, 'invalid': $v.buddy.balance.$invalid }" v-model.number="$v.buddy.balance.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.buddy.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./buddy-update.component.ts">
</script>
