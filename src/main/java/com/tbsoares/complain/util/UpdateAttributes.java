package com.tbsoares.complain.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.tbsoares.complain.util.Optional.optional;

public class UpdateAttributes {

    public static <T> T updateOnlyNotNull(T objectTo, T objectFrom, String... excludeField) {
        List<String> excludeFieldList = Arrays.asList(excludeField);

        optional(objectFrom)
                .map((Function<T, ? extends Class<?>>) T::getClass)
                .map(Class::getDeclaredFields)
                .map(Stream::of)
                .orElse(Stream.empty())
                .filter(field -> !excludeFieldList.contains(field.getName()))
                .peek(c -> c.setAccessible(true))
                .filter(f -> notNull(f, objectFrom))
                .forEach(field -> setValue(field, objectTo, objectFrom));

        return objectTo;
    }

    private static <T> Boolean notNull(Field field, T object) {
        try {
            return Utils.notNull(field.get(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static <T> void setValue(Field field, T objectTo, T objectFrom) {
        try {
            field.set(objectTo, field.get(objectFrom));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
