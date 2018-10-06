package com.trungnguyen.android.houston123.util;

/**
 * Created by trungnd4 on 06/10/2018.
 */

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import timber.log.Timber;

public final class Lists {

    private Lists() {
    }

    public static boolean startWith(List<String> list, @Nullable String s) {
        if (Lists.isEmptyOrNull(list)) {
            return false;
        }
        if (TextUtils.isEmpty(s)) {
            return false;
        }
        for (String string : list) {
            if (s.startsWith(string)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsCaseInsensitive(List<String> list, String s) {
        for (String string : list) {
            if (string.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean elementsEqual(List<T> list1, List<T> list2,
                                            BiFunction<T, T, Boolean> comparator) {
        if (list1 == null || list2 == null) {
            return false;
        }

        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            try {
                if (!comparator.apply(list1.get(i), list2.get(i))) {
                    return false;
                }
            } catch (Exception e) {
                Timber.w(e, "Error while comparing data");
                return false;
            }
        }

        return true;
    }

    public static <T> boolean isEmptyOrNull(Collection<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> boolean isEmptyOrNull(long[] list) {
        return list == null || list.length == 0;
    }

    public static <T> boolean isPresent(Collection<T> list) {
        return !isEmptyOrNull(list);
    }

    public static <T, R> List<R> transform(Collection<T> collection, Function<? super T, ? extends R> converter) {
        return transform(collection, converter, false);
    }

    public static <T, R> List<R> transform(Collection<T> collection, Function<? super T, ? extends R> converter, boolean allowNullableValue) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }

        ArrayList<R> transformedList = new ArrayList<>();
        for (T t : collection) {
            R r = null;
            try {
                r = converter.apply(t);
            } catch (Exception e) {
                Timber.w(e, "Error while transforming data");
            }
            if (r == null && !allowNullableValue) {
                continue;
            }

            transformedList.add(r);
        }

        return transformedList;
    }

    public static <T> List<List<T>> chopped(List<T> list, final int length) {
        if (length <= 0) {
            throw new RuntimeException("Split array with length " + length);
        }

        List<List<T>> parts = new ArrayList<>();
        final int size = list.size();
        for (int i = 0; i < size; i += length) {
            parts.add(new ArrayList<>(list.subList(i, Math.min(size, i + length))));
        }
        return parts;
    }

    public static <R> List<R> join(List<? extends R>... lists) {
        List<R> result = new ArrayList<>();
        for (List<? extends R> list : lists) {
            if (isEmptyOrNull(list)) {
                continue;
            }
            result.addAll(list);
        }
        return result;
    }

    public static <T> List<T> filter(List<? extends T> list, Predicate<T> predicate) {
        if (isEmptyOrNull(list)) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        for (T el : list) {
            try {
                if (predicate.test(el)) {
                    result.add(el);
                }
            } catch (Exception e) {
                Timber.d(e);
            }
        }
        return result;
    }

    /**
     * find first element in list with predicate
     *
     * @param list
     * @param predicate
     * @param <T>
     * @return
     */
    @Nullable
    public static <T> T findFirst(List<? extends T> list, Predicate<T> predicate) {
        if (isEmptyOrNull(list)) {
            return null;
        }
        for (T el : list) {
            try {
                if (predicate.test(el)) {
                    return el;
                }
            } catch (Exception e) {
                Timber.d(e);
            }
        }
        return null;
    }
}

