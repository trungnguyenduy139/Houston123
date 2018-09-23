package com.trungnguyen.android.houston123.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

public class GsonUtils {

    @NonNull
    private static final GsonBuilder gsonBuilder;

    private static Gson gson;

    private static final TypeAdapter<Number> NumberTypeAdapter = new TypeAdapter<Number>() {

        /**
         * Writes one JSON value (an array, object, string, number, boolean or null)
         * for {@code value}.
         *
         * @param out
         * @param value the Java object to write. May be null.
         */
        @Override
        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }

        /**
         * Reads one JSON value (an array, object, string, number, boolean or null)
         * and converts it to a Java object. Returns the converted object.
         *
         * @param in
         * @return the converted Java object. May be null.
         */
        @Override
        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String result = in.nextString();
            if ("".equals(result)) {
                return null;
            }
            try {
                return Long.parseLong(result);
            } catch (NumberFormatException e) {
                // empty catch exception to try another parser Double
            }

            try {
                return Double.parseDouble(result);
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }
    };

    static {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapter(long.class, NumberTypeAdapter);
        gsonBuilder.registerTypeAdapter(Long.class, NumberTypeAdapter);
        gsonBuilder.registerTypeAdapter(double.class, NumberTypeAdapter);
        gsonBuilder.registerTypeAdapter(Double.class, NumberTypeAdapter);
    }

    private static Gson instance() {
        if (gson == null) {
            gson = gsonBuilder.create();
        }
        return gson;
    }

    private static void invalidateInstance() {
        gson = null;
    }

    private static void registerTypeAdapter(Type t, Object typeAdapter) {
        gsonBuilder.registerTypeAdapter(t, typeAdapter);
        invalidateInstance();
    }

    public static String toJsonString(Object obj) {
        return instance().toJson(obj);
    }

    public static <T> T fromJsonString(String sJson, Type t) {
        if (TextUtils.isEmpty(sJson)) {
            return null;
        }
        return instance().fromJson(sJson, t);
    }

    public static <T> T fromJsonString(JsonElement jsonElement, Type t) {
        return instance().fromJson(jsonElement, t);
    }

    /**
     *
     * @param sJson the input JSON string
     * @param t the of the object to map from JSON string
     * @param typeAdapter the custom type adapter to register before deserializing
     * @param typeNeedsAdapter the type to apply the custom adapter
     * @param <T> the generic T
     * @return and object of T
     */
    public static <T> T fromJsonStringWithTypeAdapter(String sJson, Type t,
                                                      Type typeNeedsAdapter, Object typeAdapter) {
        registerTypeAdapter(typeNeedsAdapter, typeAdapter);
        invalidateInstance();
        return fromJsonString(sJson, t);
    }
}
