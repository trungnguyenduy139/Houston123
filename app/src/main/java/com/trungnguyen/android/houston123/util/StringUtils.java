package com.trungnguyen.android.houston123.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Created by trungnd4 on 23/09/2018.
 */
public class StringUtils {
    public static final String EMPTY = "";

    public static boolean safeEquals(String string1, String string2) {
        return safeEqualsIgnoreCase(string1, string2, false);
    }

    public static boolean safeEqualsIgnoreCase(String string1, String string2, boolean isIgnored) {
        if (string1 == null) {
            return string2 == null;
        }
        return isIgnored ? string1.equalsIgnoreCase(string2) : string1.equals(string2);
    }

    public static boolean isCharacterMatchPattern(@NonNull String sInput, @NonNull String sPattern) {
        Pattern pattern = Pattern.compile(sPattern, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        return pattern.matcher(sInput).find();
    }

    public static String removeSpecialCharacter(@NonNull String s) {
        if (TextUtils.isEmpty(s)) {
            return s;
        }
        return s.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        s = s.replaceAll("Đ", "D");
        s = s.replaceAll("đ", "d");
        return s;
    }
}
