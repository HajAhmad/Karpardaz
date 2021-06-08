package com.s.karpardaz.user.data;

import com.s.karpardaz.base.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface LoginService {

    @GET("users/Login")
    Call<String> login(@Query("loginPhrase") String loginPhrase);

    @GET("users/RequestRecoveryCode")
    Call<String> RequestPasswordChange(@Query("email") String email);

    @GET("users/CheckRecoveryCode")
    Call<BaseResponse<String>> sendRecoveryCode(@Query("token") String recoveryToken,
        @Query("verificationCode") String verificationCode);

    @PUT("users/ResetPassword")
    Call<Void> resetPassword(@Query("token") String recoveryToken, @Body String loginPhrase);
}
