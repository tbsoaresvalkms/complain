package com.tbsoares.complain.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UtilsTests {
    @Test
    public void itShouldReturnTrueWhenIsNotNull() {
        Boolean aBoolean = Utils.notNull(new Object());

        assertThat(aBoolean).isTrue();
    }

    @Test
    public void itShouldReturnFalseWhenIsNull() {
        Boolean aBoolean = Utils.notNull(null);

        assertThat(aBoolean).isFalse();
    }

    @Test
    public void itShouldReturnTrueWhenListIsNotEmpty() {
        Boolean aBoolean = Utils.notEmpty(Collections.singletonList(new Object()));

        assertThat(aBoolean).isTrue();
    }

    @Test
    public void itShouldReturnFalseWhenListIsEmpty() {
        Boolean aBoolean = Utils.notEmpty(new ArrayList<>());

        assertThat(aBoolean).isFalse();
    }
}
