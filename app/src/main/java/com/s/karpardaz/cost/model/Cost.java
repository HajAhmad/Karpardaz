package com.s.karpardaz.cost.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;

import com.s.karpardaz.base.model.BaseFin;

@Entity(indices = {@Index(value = {"uuid", "id"}, unique = true)})
public class Cost extends BaseFin {

    public Cost(long id,
            @NonNull String uuid,
            @NonNull String atDate,
            @NonNull String subject,
            @NonNull String amount,
            @NonNull String stockId,
            @NonNull String createdAt,
            @NonNull String updatedAt) {
        super(id, uuid, atDate, subject, amount, stockId, createdAt, updatedAt);
    }

    @NonNull
    @Override
    public String toString() {
        return "Cost{" +
                "purchasedAt='" + atDate + '\'' +
                ", subject='" + subject + '\'' +
                ", amount='" + amount + '\'' +
                "," + super.toString() +
                '}';
    }
}
