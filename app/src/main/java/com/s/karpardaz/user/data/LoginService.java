package com.s.karpardaz.user.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginService {

    @GET("users/Login")
    Call<String> login(@Query("loginPhrase") String loginPhrase);

    @GET("users/RequestPasswordChange")
    Call<Void> RequestPasswordChange(@Query("email") String email);
}
