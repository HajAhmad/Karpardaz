package com.s.karpardaz.base;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;


import static java.util.Objects.requireNonNull;

public abstract class BaseModel {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    protected long id;
    @SerializedName("uuid")
    @Expose
    protected String uuid;

    public BaseModel(long id, @NonNull String uuid) {
        this.id = id;
        this.uuid = requireNonNull(uuid);
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModel baseModel = (BaseModel) o;
        return id == baseModel.id &&
                uuid.equals(baseModel.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid);
    }


    @NonNull
    @Override
    public String toString() {
        return "id=" + id +
                ", uuid='" + uuid + '\'';
    }
}
