import { IUser } from '@/shared/model/user.model';
import { IBankAccount } from '@/shared/model/bank-account.model';
import { IContactRelationship } from '@/shared/model/contact-relationship.model';

export interface IBuddy {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  phoneNumber?: number;
  balance?: number;
  user?: IUser;
  bankAccount?: IBankAccount;
  contactRelationships?: IContactRelationship[];
}

export class Buddy implements IBuddy {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public phoneNumber?: number,
    public balance?: number,
    public user?: IUser,
    public bankAccount?: IBankAccount,
    public contactRelationships?: IContactRelationship[]
  ) {}
}
