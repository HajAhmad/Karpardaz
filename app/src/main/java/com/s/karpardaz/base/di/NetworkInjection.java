package com.s.karpardaz.base.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.s.karpardaz.base.util.AppConstants.API_URL;

public abstract class NetworkInjection {

    private static final int TIMEOUT_IN_SECONDS = 30;

    private NetworkInjection() {}

    private static Gson provideGson() {
        return new GsonBuilder()
            .setLenient()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static ConnectionSpec provideConnectionSpec() {
        return new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
            .build();
    }

    private static OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            //.connectionSpecs(Collections.singletonList(provideConnectionSpec()))
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
                        .baseUrl(API_URL)
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
