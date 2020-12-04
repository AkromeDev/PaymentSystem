package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class BuddyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Buddy.class);
        Buddy buddy1 = new Buddy();
        buddy1.setId(1L);
        Buddy buddy2 = new Buddy();
        buddy2.setId(buddy1.getId());
        assertThat(buddy1).isEqualTo(buddy2);
        buddy2.setId(2L);
        assertThat(buddy1).isNotEqualTo(buddy2);
        buddy1.setId(null);
        assertThat(buddy1).isNotEqualTo(buddy2);
    }
}
