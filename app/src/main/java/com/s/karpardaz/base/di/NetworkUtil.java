package com.s.karpardaz.base.di;

import java.net.HttpURLConnection;

import retrofit2.Response;

public final class NetworkUtil {

    public static boolean isAuthenticationError(int httpCode){
        return httpCode == HttpURLConnection.HTTP_UNAUTHORIZED;
    }

    public static boolean isInternalServerError(int httpCode){
        return httpCode == HttpURLConnection.HTTP_INTERNAL_ERROR;
    }

    public static boolean isResponseValid(Response<?> response){
        return response != null &&
                response.isSuccessful();
    }
}
