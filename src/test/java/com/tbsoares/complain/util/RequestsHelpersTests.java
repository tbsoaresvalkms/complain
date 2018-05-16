package com.tbsoares.complain.util;

import com.tbsoares.complain.domain.Complain;
import com.tbsoares.complain.exception.UnprocessableEntityException;
import org.junit.Test;

public class RequestsHelpersTests {

    @Test
    public void itNoShouldThrowExceptionWhenFieldsAreNotValue() {
        RequestsHelpers.ifAlreadyHaveSomeFieldsThrowException(new Complain(), "id");
    }

    @Test(expected = UnprocessableEntityException.class)
    public void itShouldThrowExceptionWhenFieldsAreValue() {
        Complain complain = new Complain();
        complain.setId("123");
        RequestsHelpers.ifAlreadyHaveSomeFieldsThrowException(complain, "id");
    }
}
