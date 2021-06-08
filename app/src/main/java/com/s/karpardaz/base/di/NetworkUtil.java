package com.s.karpardaz.base.di;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Response;

public final class NetworkUtil {

    public static boolean isAuthenticationError(int httpCode) {
        return httpCode == HttpURLConnection.HTTP_UNAUTHORIZED;
    }

    public static boolean isInternalServerError(int httpCode) {
        return httpCode == HttpURLConnection.HTTP_INTERNAL_ERROR;
    }

    public static boolean isResponseSuccessful(Response<?> response) {
        return response != null &&
            response.isSuccessful();
    }

    public static Throwable produceUnknownException(int responseCode,
        ResponseBody errorBody) {
        StringBuilder sb = new StringBuilder();

        if (errorBody != null) {
            try {
                sb.append(errorBody.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sb.append("خطای ناشناخته:".concat(String.valueOf(responseCode)));
        return new Throwable(sb.toString());
    }

}
