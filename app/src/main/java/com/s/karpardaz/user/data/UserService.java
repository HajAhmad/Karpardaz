package com.s.karpardaz.user.data;

import com.s.karpardaz.base.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("users/Register")
    Call<String> register(@Body User user);

}
