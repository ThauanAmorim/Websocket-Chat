package com.chat.server.infrastructure.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class CollectionsUtils {

    private CollectionsUtils() {}

    public static boolean isEmpty(Object obj) {
        if (Objects.isNull(obj)) return true;

        if (obj instanceof Collection)
            return ((Collection<?>)obj).isEmpty();
        if (obj instanceof Object[])
            return ((Object[]) obj).length == 0;
        if (obj instanceof Map)
            return ((Map<?, ?>) obj).isEmpty();
        if (obj instanceof Iterator)
            return !((Iterator<?>) obj).hasNext();
        if (obj instanceof Iterable)
            return !((Iterable<?>)obj).iterator().hasNext();

        return true;
    }

    public static boolean notEmpty(Object obj) {
        return !isEmpty(obj);
    }


}
