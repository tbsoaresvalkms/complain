package com.tbsoares.complain.util;

import com.tbsoares.complain.domain.Complain;
import org.junit.Test;

import static com.tbsoares.complain.util.Optional.optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OptionalTests {

    @Test
    public void itShouldReturnAnOptional() {
        Complain complain = new Complain();
        java.util.Optional<Complain> optional = optional(complain);

        assertThat(optional).isInstanceOf(java.util.Optional.class);
    }

    @Test
    public void itShouldReturnAnOptionalWhenParameterIsNull() {
        java.util.Optional<Complain> optional = optional(null);

        assertThat(optional).isInstanceOf(java.util.Optional.class);
    }
}
