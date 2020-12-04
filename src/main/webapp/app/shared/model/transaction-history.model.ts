import { IBankAccount } from '@/shared/model/bank-account.model';
import { IBuddy } from '@/shared/model/buddy.model';
import { IMyTransaction } from '@/shared/model/my-transaction.model';

export interface ITransactionHistory {
  id?: number;
  recipientMail?: string;
  date?: Date;
  amount?: number;
  description?: string;
  bankAccount?: IBankAccount;
  buddy?: IBuddy;
  myTransactions?: IMyTransaction[];
}

export class TransactionHistory implements ITransactionHistory {
  constructor(
    public id?: number,
    public recipientMail?: string,
    public date?: Date,
    public amount?: number,
    public description?: string,
    public bankAccount?: IBankAccount,
    public buddy?: IBuddy,
    public myTransactions?: IMyTransaction[]
  ) {}
}
