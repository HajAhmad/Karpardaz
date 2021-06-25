package com.s.karpardaz.base.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRemoteDto {
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Password")
    @Expose
    private String password;

    public LoginRemoteDto(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
