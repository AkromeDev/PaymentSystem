package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ContactRelationship.
 */
@Entity
@Table(name = "contact_relationship")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ContactRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "user_id_1")
    private Long userId1;

    @Column(name = "user_id_2")
    private Long userId2;

    @ManyToOne
    @JsonIgnoreProperties(value = "contactRelationships", allowSetters = true)
    private Buddy buddy;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId1() {
        return userId1;
    }

    public ContactRelationship userId1(Long userId1) {
        this.userId1 = userId1;
        return this;
    }

    public void setUserId1(Long userId1) {
        this.userId1 = userId1;
    }

    public Long getUserId2() {
        return userId2;
    }

    public ContactRelationship userId2(Long userId2) {
        this.userId2 = userId2;
        return this;
    }

    public void setUserId2(Long userId2) {
        this.userId2 = userId2;
    }

    public Buddy getBuddy() {
        return buddy;
    }

    public ContactRelationship buddy(Buddy buddy) {
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
        if (!(o instanceof ContactRelationship)) {
            return false;
        }
        return id != null && id.equals(((ContactRelationship) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContactRelationship{" +
            "id=" + getId() +
            ", userId1=" + getUserId1() +
            ", userId2=" + getUserId2() +
            "}";
    }
}
