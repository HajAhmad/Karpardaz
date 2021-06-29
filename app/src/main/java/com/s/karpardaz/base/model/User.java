package com.s.karpardaz.base.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(indices = {@Index(value = {"id", "uuid"},
    unique = true)})
public class User extends BaseEntity {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Password")
    @Expose
    private String password;

    public User(long id, @NonNull String uuid, String name, String email, String password,
            String createdAt, String updatedAt) {
        super(id, uuid, createdAt, updatedAt);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Ignore
    public User(String uuid, String name, String email, String password, String createdAt) {
        this(0, uuid, name, email, password, createdAt, null);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
