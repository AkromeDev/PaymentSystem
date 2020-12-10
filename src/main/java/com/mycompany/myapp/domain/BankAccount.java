package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BankAccount.
 */
@Entity
@Table(name = "bank_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "iban", nullable = false)
    private Long iban;

    @NotNull
    @Column(name = "bic", nullable = false)
    private Long bic;

    @OneToOne(mappedBy = "bankAccount")
    @JsonIgnore
    private Buddy buddy;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BankAccount name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIban() {
        return iban;
    }

    public BankAccount iban(Long iban) {
        this.iban = iban;
        return this;
    }

    public void setIban(Long iban) {
        this.iban = iban;
    }

    public Long getBic() {
        return bic;
    }

    public BankAccount bic(Long bic) {
        this.bic = bic;
        return this;
    }

    public void setBic(Long bic) {
        this.bic = bic;
    }

    public Buddy getBuddy() {
        return buddy;
    }

    public BankAccount buddy(Buddy buddy) {
        this.buddy = buddy;
        return this;
    }

    public void setBuddy(Buddy buddy) {
        this.buddy = buddy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccount)) {
            return false;
        }
        return id != null && id.equals(((BankAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankAccount{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", iban=" + getIban() +
            ", bic=" + getBic() +
            "}";
    }
}
