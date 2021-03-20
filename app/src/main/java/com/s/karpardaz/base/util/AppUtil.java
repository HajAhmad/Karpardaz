package com.s.karpardaz.base.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

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

    public static boolean isEmailValid(String email) {
        return Pattern.compile(
                "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE)
                .matcher(email)
                .matches();
    }

    public static boolean inNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
