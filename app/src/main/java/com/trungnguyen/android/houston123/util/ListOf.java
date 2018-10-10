package com.trungnguyen.android.houston123.util;

/**
 * Created by trungnd4 on 10/10/2018.
 */

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ListOf<T> implements ParameterizedType {
    private final Class<T> type;

    public ListOf(Class<T> type) {
        this.type = type;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{type};
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
