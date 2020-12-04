import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Buddy = () => import('@/entities/buddy/buddy.vue');
// prettier-ignore
const BuddyUpdate = () => import('@/entities/buddy/buddy-update.vue');
// prettier-ignore
const BuddyDetails = () => import('@/entities/buddy/buddy-details.vue');
// prettier-ignore
const ContactRelationship = () => import('@/entities/contact-relationship/contact-relationship.vue');
// prettier-ignore
const ContactRelationshipUpdate = () => import('@/entities/contact-relationship/contact-relationship-update.vue');
// prettier-ignore
const ContactRelationshipDetails = () => import('@/entities/contact-relationship/contact-relationship-details.vue');
// prettier-ignore
const BankAccount = () => import('@/entities/bank-account/bank-account.vue');
// prettier-ignore
const BankAccountUpdate = () => import('@/entities/bank-account/bank-account-update.vue');
// prettier-ignore
const BankAccountDetails = () => import('@/entities/bank-account/bank-account-details.vue');
// prettier-ignore
const TransactionHistory = () => import('@/entities/transaction-history/transaction-history.vue');
// prettier-ignore
const TransactionHistoryUpdate = () => import('@/entities/transaction-history/transaction-history-update.vue');
// prettier-ignore
const TransactionHistoryDetails = () => import('@/entities/transaction-history/transaction-history-details.vue');
// prettier-ignore
const MyTransaction = () => import('@/entities/my-transaction/my-transaction.vue');
// prettier-ignore
const MyTransactionUpdate = () => import('@/entities/my-transaction/my-transaction-update.vue');
// prettier-ignore
const MyTransactionDetails = () => import('@/entities/my-transaction/my-transaction-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/buddy',
    name: 'Buddy',
    component: Buddy,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/buddy/new',
    name: 'BuddyCreate',
    component: BuddyUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/buddy/:buddyId/edit',
    name: 'BuddyEdit',
    component: BuddyUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/buddy/:buddyId/view',
    name: 'BuddyView',
    component: BuddyDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contact-relationship',
    name: 'ContactRelationship',
    component: ContactRelationship,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contact-relationship/new',
    name: 'ContactRelationshipCreate',
    component: ContactRelationshipUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contact-relationship/:contactRelationshipId/edit',
    name: 'ContactRelationshipEdit',
    component: ContactRelationshipUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contact-relationship/:contactRelationshipId/view',
    name: 'ContactRelationshipView',
    component: ContactRelationshipDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bank-account',
    name: 'BankAccount',
    component: BankAccount,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bank-account/new',
    name: 'BankAccountCreate',
    component: BankAccountUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bank-account/:bankAccountId/edit',
    name: 'BankAccountEdit',
    component: BankAccountUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bank-account/:bankAccountId/view',
    name: 'BankAccountView',
    component: BankAccountDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/transaction-history',
    name: 'TransactionHistory',
    component: TransactionHistory,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/transaction-history/new',
    name: 'TransactionHistoryCreate',
    component: TransactionHistoryUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/transaction-history/:transactionHistoryId/edit',
    name: 'TransactionHistoryEdit',
    component: TransactionHistoryUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/transaction-history/:transactionHistoryId/view',
    name: 'TransactionHistoryView',
    component: TransactionHistoryDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/my-transaction',
    name: 'MyTransaction',
    component: MyTransaction,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/my-transaction/new',
    name: 'MyTransactionCreate',
    component: MyTransactionUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/my-transaction/:myTransactionId/edit',
    name: 'MyTransactionEdit',
    component: MyTransactionUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/my-transaction/:myTransactionId/view',
    name: 'MyTransactionView',
    component: MyTransactionDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/transaction-history',
    name: 'TransactionHistory',
    component: TransactionHistory,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/transaction-history/new',
    name: 'TransactionHistoryCreate',
    component: TransactionHistoryUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/transaction-history/:transactionHistoryId/edit',
    name: 'TransactionHistoryEdit',
    component: TransactionHistoryUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/transaction-history/:transactionHistoryId/view',
    name: 'TransactionHistoryView',
    component: TransactionHistoryDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
