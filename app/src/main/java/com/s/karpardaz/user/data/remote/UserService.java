package com.s.karpardaz.user.data.remote;

import com.s.karpardaz.user.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST("users")
    Call<String> register(@Body User user);

    @GET("")
    Call<String> login(@Query("loginPhrase") String loginPhrase);
}
