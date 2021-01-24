package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A MyTransaction.
 */
@Entity
@Table(name = "my_transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MyTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "amount")
    private Long amount;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "my_transaction_transaction_history",
               joinColumns = @JoinColumn(name = "my_transaction_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "transaction_history_id", referencedColumnName = "id"))
    private Set<TransactionHistory> transactionHistories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public MyTransaction email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAmount() {
        return amount;
    }

    public MyTransaction amount(Long amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Set<TransactionHistory> getTransactionHistories() {
        return transactionHistories;
    }

    public MyTransaction transactionHistories(Set<TransactionHistory> transactionHistories) {
        this.transactionHistories = transactionHistories;
        return this;
    }

    public MyTransaction addTransactionHistory(TransactionHistory transactionHistory) {
        this.transactionHistories.add(transactionHistory);
        transactionHistory.getMyTransactions().add(this);
        return this;
    }

    public MyTransaction removeTransactionHistory(TransactionHistory transactionHistory) {
        this.transactionHistories.remove(transactionHistory);
        transactionHistory.getMyTransactions().remove(this);
        return this;
    }

    public void setTransactionHistories(Set<TransactionHistory> transactionHistories) {
        this.transactionHistories = transactionHistories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MyTransaction)) {
            return false;
        }
        return id != null && id.equals(((MyTransaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MyTransaction{" +
            "id=" + getId() +
            ", email='" + getEmail() + "'" +
            ", amount=" + getAmount() +
            "}";
    }
}
