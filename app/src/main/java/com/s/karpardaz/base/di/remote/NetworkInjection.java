package com.s.karpardaz.base.di.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkInjection {

    private static final String BASE_URL = "";
    private static final int TIMEOUT_IN_SECONDS = 30;

    private NetworkInjection() {}

    private static Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request authorisedRequest = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build();
                    return chain.proceed(authorisedRequest);
                });

        return httpClient.build();
    }

    private static volatile Retrofit sRetrofit;

    private static Retrofit providesRetrofit(Gson gson, OkHttpClient httpClient) {
        if (sRetrofit == null) {
            synchronized (NetworkInjection.class) {
                if (sRetrofit == null) {
                    sRetrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(httpClient)
                            .build();
                }
            }
        }
        return sRetrofit;
    }

    public static <T> T provideService(final Class<T> modelClass) {
        return providesRetrofit(provideGson(), providesOkHttpClient(provideHttpLoggingInterceptor()))
                .create(modelClass);
    }

}
