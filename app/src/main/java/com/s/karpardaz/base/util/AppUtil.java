package com.s.karpardaz.base.util;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class AppUtil {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final TimeZone DEFAULT_TIME_ZONE;
    public static final Locale DEFAULT_LOCALE;

    static {
        DEFAULT_TIME_ZONE = TimeZone.getTimeZone("GMT");
        DEFAULT_LOCALE = Locale.US;
    }

    private AppUtil() {
    }

    @NonNull
    public static String getCurrentDateTimeUTC() {
        DateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN, Locale.US);
        format.setTimeZone(DEFAULT_TIME_ZONE);
        return format.format(new Date());
    }

    public static Date strDateToDate(String source) throws ParseException {
        DateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN, Locale.US);
        format.setTimeZone(DEFAULT_TIME_ZONE);
        return format.parse(source);
    }

    public static boolean isAnyEmpty(String... args) {
        for (String str : args) {
            if (TextUtils.isEmpty(str.trim()))
                return false;
        }
        return true;
    }

}
