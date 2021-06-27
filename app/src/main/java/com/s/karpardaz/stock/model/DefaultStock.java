package com.s.karpardaz.stock.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.s.karpardaz.base.model.BaseEntity;

@Entity
public class DefaultStock extends BaseEntity {

    public DefaultStock(long id, @NonNull String uuid, String createdAt, String updatedAt) {
        super(id, uuid, createdAt, updatedAt);
    }

    @Ignore
    public DefaultStock(@NonNull String uuid, String createdAt) {
        super(0, uuid, createdAt, "");
    }


}
