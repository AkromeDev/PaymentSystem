package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class MyTransactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MyTransaction.class);
        MyTransaction myTransaction1 = new MyTransaction();
        myTransaction1.setId(1L);
        MyTransaction myTransaction2 = new MyTransaction();
        myTransaction2.setId(myTransaction1.getId());
        assertThat(myTransaction1).isEqualTo(myTransaction2);
        myTransaction2.setId(2L);
        assertThat(myTransaction1).isNotEqualTo(myTransaction2);
        myTransaction1.setId(null);
        assertThat(myTransaction1).isNotEqualTo(myTransaction2);
    }
}
