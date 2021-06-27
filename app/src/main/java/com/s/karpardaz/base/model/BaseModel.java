package com.s.karpardaz.base.model;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public abstract class BaseModel {

    @NonNull
    @SerializedName("Id")
    @Expose
    protected final String uuid;

    @SerializedName("CreatedAt")
    @Expose
    private String createdAt;
    @SerializedName("UpdatedAt")
    @Expose
    private String updatedAt;

    public BaseModel(@NonNull String uuid) {
        this.uuid = uuid;
    }

    public BaseModel(@NonNull String uuid, String createdAt, String updatedAt) {
        this(uuid);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public boolean isChanged() {
        return TextUtils.equals(updatedAt, updatedAt);
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModel baseModel = (BaseModel) o;
        return uuid.equals(baseModel.uuid) &&
                createdAt.equals(baseModel.createdAt) &&
                updatedAt.equals(baseModel.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, createdAt, updatedAt);
    }

    @NonNull
    @Override
    public String toString() {
        return "BaseModel{" +
                "uuid='" + uuid + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
