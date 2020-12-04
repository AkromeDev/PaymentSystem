package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A TransactionHistory.
 */
@Entity
@Table(name = "transaction_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TransactionHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "recipient_mail")
    private String recipientMail;

    @Column(name = "date")
    private Instant date;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(unique = true)
    private BankAccount bankAccount;

    @OneToOne
    @JoinColumn(unique = true)
    private Buddy buddy;

    @ManyToMany(mappedBy = "transactionHistories")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<MyTransaction> myTransactions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientMail() {
        return recipientMail;
    }

    public TransactionHistory recipientMail(String recipientMail) {
        this.recipientMail = recipientMail;
        return this;
    }

    public void setRecipientMail(String recipientMail) {
        this.recipientMail = recipientMail;
    }

    public Instant getDate() {
        return date;
    }

    public TransactionHistory date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getAmount() {
        return amount;
    }

    public TransactionHistory amount(Long amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public TransactionHistory description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public TransactionHistory bankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Buddy getBuddy() {
        return buddy;
    }

    public TransactionHistory buddy(Buddy buddy) {
        this.buddy = buddy;
        return this;
    }

    public void setBuddy(Buddy buddy) {
        this.buddy = buddy;
    }

    public Set<MyTransaction> getMyTransactions() {
        return myTransactions;
    }

    public TransactionHistory myTransactions(Set<MyTransaction> myTransactions) {
        this.myTransactions = myTransactions;
        return this;
    }

    public TransactionHistory addMyTransaction(MyTransaction myTransaction) {
        this.myTransactions.add(myTransaction);
        myTransaction.getTransactionHistories().add(this);
        return this;
    }

    public TransactionHistory removeMyTransaction(MyTransaction myTransaction) {
        this.myTransactions.remove(myTransaction);
        myTransaction.getTransactionHistories().remove(this);
        return this;
    }

    public void setMyTransactions(Set<MyTransaction> myTransactions) {
        this.myTransactions = myTransactions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionHistory)) {
            return false;
        }
        return id != null && id.equals(((TransactionHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionHistory{" +
            "id=" + getId() +
            ", recipientMail='" + getRecipientMail() + "'" +
            ", date='" + getDate() + "'" +
            ", amount=" + getAmount() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
