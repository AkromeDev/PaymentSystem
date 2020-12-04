package com.mycompany.myapp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Buddy entity.
 */
@ApiModel(description = "The Buddy entity.")
@Entity
@Table(name = "buddy")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Buddy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * The firstname attribute.
     */
    @NotNull
    @ApiModelProperty(value = "The firstname attribute.", required = true)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "balance")
    private Long balance;

    @OneToMany(mappedBy = "buddy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<BankAccount> bankAccounts = new HashSet<>();

    @OneToMany(mappedBy = "buddy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ContactRelationship> contactRelationships = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Buddy firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Buddy lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Buddy email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Buddy phoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getBalance() {
        return balance;
    }

    public Buddy balance(Long balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public Buddy bankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
        return this;
    }

    public Buddy addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.add(bankAccount);
        bankAccount.setBuddy(this);
        return this;
    }

    public Buddy removeBankAccount(BankAccount bankAccount) {
        this.bankAccounts.remove(bankAccount);
        bankAccount.setBuddy(null);
        return this;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public Set<ContactRelationship> getContactRelationships() {
        return contactRelationships;
    }

    public Buddy contactRelationships(Set<ContactRelationship> contactRelationships) {
        this.contactRelationships = contactRelationships;
        return this;
    }

    public Buddy addContactRelationship(ContactRelationship contactRelationship) {
        this.contactRelationships.add(contactRelationship);
        contactRelationship.setBuddy(this);
        return this;
    }

    public Buddy removeContactRelationship(ContactRelationship contactRelationship) {
        this.contactRelationships.remove(contactRelationship);
        contactRelationship.setBuddy(null);
        return this;
    }

    public void setContactRelationships(Set<ContactRelationship> contactRelationships) {
        this.contactRelationships = contactRelationships;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Buddy)) {
            return false;
        }
        return id != null && id.equals(((Buddy) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Buddy{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", balance=" + getBalance() +
            "}";
    }
}
