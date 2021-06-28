package com.s.karpardaz.base.util;

import static java.util.Objects.requireNonNull;

public final class AppConstants {
    private AppConstants() {}

    public static final String API_URL = "http://192.168.43.30:5000/api/";

    public static final String EMPTY_UUID = "00000000-0000-0000-0000-000000000000";

    public static String sActiveUserId;

    private static String sDefaultStockId;
    private static String sDefaultStockCurrency;

    public static String getsDefaultStockCurrency() {
        return sDefaultStockCurrency;
    }

    public static void setsDefaultStockCurrency(String sDefaultStockCurrency) {
        AppConstants.sDefaultStockCurrency = sDefaultStockCurrency;
    }

    public static String getDefaultStockId() {
        return sDefaultStockId;
    }

    public static void setDefaultStockId(String id){
        sDefaultStockId = requireNonNull(id);
    }

}
