import { IBuddy } from '@/shared/model/buddy.model';

export interface IContactRelationship {
  id?: number;
  userId1?: number;
  userId2?: number;
  buddy?: IBuddy;
}

export class ContactRelationship implements IContactRelationship {
  constructor(public id?: number, public userId1?: number, public userId2?: number, public buddy?: IBuddy) {}
}
