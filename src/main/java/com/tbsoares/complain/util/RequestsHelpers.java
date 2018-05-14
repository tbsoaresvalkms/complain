package com.tbsoares.complain.util;

import com.google.common.base.CaseFormat;
import com.tbsoares.complain.exception.UnprocessableEntityException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.util.Arrays;

import static com.tbsoares.complain.util.Utils.notNull;

public class RequestsHelpers {

    public static <T> void ifAlreadyHaveSomeFieldsThrowException(T obj, String... fields) {
        Arrays.stream(fields).forEach(field -> isThere(obj, field));
    }

    public static void verifyErrors(Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldErrors()
                    .stream()
                    .map(RequestsHelpers::formatterError)
                    .reduce("There is invalid fields", String::concat);
            throw new UnprocessableEntityException(message);
        }
    }

    private static <T> void isThere(T t, String field) {
        try {
            Field idField = t.getClass().getDeclaredField(field);
            idField.setAccessible(Boolean.TRUE);
            Object object = idField.get(t);

            if (notNull(object)) {
                throw new UnprocessableEntityException("The object cannot already have a " + field);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static String formatterError(FieldError fieldError) {
        String fieldName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldError.getField());
        return String.format(", %s: %s", fieldName, fieldError.getDefaultMessage());
    }
}
