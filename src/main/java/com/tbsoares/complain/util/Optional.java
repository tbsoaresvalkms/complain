package com.tbsoares.complain.util;

public class Optional {
    public static <T> java.util.Optional<T> optional(T obj) {
        return java.util.Optional.ofNullable(obj);
    }
}
