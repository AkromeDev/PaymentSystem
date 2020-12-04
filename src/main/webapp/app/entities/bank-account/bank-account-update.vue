<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="paymentSystemApp.bankAccount.home.createOrEditLabel">Create or edit a BankAccount</h2>
                <div>
                    <div class="form-group" v-if="bankAccount.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="bankAccount.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bank-account-name">Name</label>
                        <input type="text" class="form-control" name="name" id="bank-account-name"
                            :class="{'valid': !$v.bankAccount.name.$invalid, 'invalid': $v.bankAccount.name.$invalid }" v-model="$v.bankAccount.name.$model"  required/>
                        <div v-if="$v.bankAccount.name.$anyDirty && $v.bankAccount.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.bankAccount.name.required">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bank-account-iban">Iban</label>
                        <input type="number" class="form-control" name="iban" id="bank-account-iban"
                            :class="{'valid': !$v.bankAccount.iban.$invalid, 'invalid': $v.bankAccount.iban.$invalid }" v-model.number="$v.bankAccount.iban.$model"  required/>
                        <div v-if="$v.bankAccount.iban.$anyDirty && $v.bankAccount.iban.$invalid">
                            <small class="form-text text-danger" v-if="!$v.bankAccount.iban.required">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.bankAccount.iban.numeric">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bank-account-bic">Bic</label>
                        <input type="number" class="form-control" name="bic" id="bank-account-bic"
                            :class="{'valid': !$v.bankAccount.bic.$invalid, 'invalid': $v.bankAccount.bic.$invalid }" v-model.number="$v.bankAccount.bic.$model"  required/>
                        <div v-if="$v.bankAccount.bic.$anyDirty && $v.bankAccount.bic.$invalid">
                            <small class="form-text text-danger" v-if="!$v.bankAccount.bic.required">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.bankAccount.bic.numeric">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bank-account-buddy">Buddy</label>
                        <select class="form-control" id="bank-account-buddy" name="buddy" v-model="bankAccount.buddy">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="bankAccount.buddy && buddyOption.id === bankAccount.buddy.id ? bankAccount.buddy : buddyOption" v-for="buddyOption in buddies" :key="buddyOption.id">{{buddyOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.bankAccount.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./bank-account-update.component.ts">
</script>
