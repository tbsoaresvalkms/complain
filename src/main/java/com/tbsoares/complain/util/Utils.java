package com.tbsoares.complain.util;

import java.util.List;

public class Utils {
    public static Boolean notNull(Object object) {
        return object != null;
    }

    public static <T> Boolean notEmpty(List<T> list) {
        return list != null && !list.isEmpty();
    }
}
