import { ITransactionHistory } from '@/shared/model/transaction-history.model';

export interface IMyTransaction {
  id?: number;
  email?: string;
  amount?: number;
  transactionHistories?: ITransactionHistory[];
}

export class MyTransaction implements IMyTransaction {
  constructor(public id?: number, public email?: string, public amount?: number, public transactionHistories?: ITransactionHistory[]) {}
}
