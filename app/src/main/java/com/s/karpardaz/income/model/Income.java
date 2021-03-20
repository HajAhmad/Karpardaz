package com.s.karpardaz.income.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;

import com.s.karpardaz.base.model.BaseFin;

@Entity(indices = {@Index(value = {"uuid", "id"}, unique = true)})
public class Income extends BaseFin {

    public Income(long id, @NonNull String uuid,
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
        return "Income{" +
                "atDate='" + atDate + '\'' +
                ", subject='" + subject + '\'' +
                ", amount='" + amount + '\'' +
                ", stockId='" + stockId + '\'' +
                ", id=" + id +
                ", uuid='" + uuid + '\'' +
                "} " + super.toString();
    }
}
