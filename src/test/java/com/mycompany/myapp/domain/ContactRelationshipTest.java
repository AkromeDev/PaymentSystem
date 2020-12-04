package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ContactRelationshipTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactRelationship.class);
        ContactRelationship contactRelationship1 = new ContactRelationship();
        contactRelationship1.setId(1L);
        ContactRelationship contactRelationship2 = new ContactRelationship();
        contactRelationship2.setId(contactRelationship1.getId());
        assertThat(contactRelationship1).isEqualTo(contactRelationship2);
        contactRelationship2.setId(2L);
        assertThat(contactRelationship1).isNotEqualTo(contactRelationship2);
        contactRelationship1.setId(null);
        assertThat(contactRelationship1).isNotEqualTo(contactRelationship2);
    }
}
