package com.s.karpardaz.base.model;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

public class BaseEntity extends BaseModel {

    @PrimaryKey(autoGenerate = true)
    protected final long id;

    public BaseEntity(long id, @NonNull String uuid, String createdAt,
            String updatedAt) {
        super(uuid, createdAt, updatedAt);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
