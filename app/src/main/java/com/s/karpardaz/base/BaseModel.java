package com.s.karpardaz.base;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import java.util.Objects;


import static java.util.Objects.requireNonNull;

public abstract class BaseModel {
    @PrimaryKey
    protected long id;
    @NonNull
    protected String uuid;
    protected boolean isBackedUp;

    public BaseModel(long id, @NonNull String uuid) {
        this.id = id;
        this.uuid = requireNonNull(uuid);

        isBackedUp = false;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public boolean isBackedUp() {
        return isBackedUp;
    }

    public void setBackedUp(boolean backedUp) {
        isBackedUp = backedUp;
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
