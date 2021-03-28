package com.s.karpardaz.user.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Login")
public class Login {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String userId;
    private String createdAt;

    public Login(String userId, String createdAt) {
        this.id = 0;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
