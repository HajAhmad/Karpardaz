package com.s.karpardaz.user.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;

import com.s.karpardaz.base.model.BaseEntity;

@Entity(tableName = "User", indices = {@Index(value = {"id", "uuid"}, unique = true)})
public class User extends BaseEntity {

    private String name;
    private String email;
    private String password;


    public User(long id, @NonNull String uuid, String name, String email, String password,
            String createdAt, String updatedAt) {
        super(id, uuid, createdAt, updatedAt);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Ignore
    public User(String name, String email, String password, String createdAt) {
        this(0, "", name, email, password, createdAt, null);
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
