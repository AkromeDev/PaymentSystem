import { IBuddy } from '@/shared/model/buddy.model';

export interface IBankAccount {
  id?: number;
  name?: string;
  iban?: number;
  bic?: number;
  buddy?: IBuddy;
}

export class BankAccount implements IBankAccount {
  constructor(public id?: number, public name?: string, public iban?: number, public bic?: number, public buddy?: IBuddy) {}
}
