package com.s.karpardaz.base.model;

import androidx.annotation.NonNull;

public class UserRemoteDto {

    private final String uuid;
    private final String email;
    private final String name;
    private final String password;
    private final String createdAt;
    private final String updatedAt;

    public UserRemoteDto(String uuid, String email, String name, String password,
            String createdAt, String updatedAt) {
        this.uuid = uuid;
        this.email = email;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getUuid() {
        return uuid;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserRemoteDto{" +
                "uuid='" + uuid + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
