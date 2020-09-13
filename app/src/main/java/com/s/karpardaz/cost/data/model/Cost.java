package com.s.karpardaz.cost.data.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.s.karpardaz.base.util.DateUtil;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "tbl_cost",
        primaryKeys = {"Id"})
public class Cost {

    @NonNull
    @ColumnInfo(name = "Id")
    private final String id;
    @NonNull
    @ColumnInfo(name = "CreationDateTime")
    private final String creationDateTime;
    @NonNull
    @ColumnInfo(name = "LastModifiedDateTime")
    private String lastModifiedDateTime;
    @NonNull
    @ColumnInfo(name = "Subject")
    private String subject;
    @NonNull
    @ColumnInfo(name = "Amount")
    private String amount;

    public Cost(@NonNull String id,
            @NonNull String creationDateTime,
            @NonNull String lastModifiedDateTime,
            @NonNull String subject,
            @NonNull String amount) {
        this.id = id;
        this.creationDateTime = creationDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.subject = subject;
        this.amount = amount;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getCreationDateTime() {
        return creationDateTime;
    }

    @NonNull
    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    @NonNull
    public String getSubject() {
        return subject;
    }

    @NonNull
    public String getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cost cost = (Cost) o;
        return id.equals(cost.id) &&
                creationDateTime.equals(cost.creationDateTime) &&
                lastModifiedDateTime.equals(cost.lastModifiedDateTime) &&
                subject.equals(cost.subject) &&
                amount.equals(cost.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDateTime, lastModifiedDateTime, subject, amount);
    }

    @NonNull
    @Override
    public String toString() {
        return "Cost{\n" +
                "id='" + id + '\n' +
                ", creationDateTime='" + creationDateTime + '\n' +
                ", lastModifiedDateTime='" + lastModifiedDateTime + '\n' +
                ", subject='" + subject + '\n' +
                ", amount='" + amount + '\n' +
                '}';
    }

    @Ignore
    public static Cost getSample() {
        return new Cost(
                UUID.randomUUID().toString(),
                DateUtil.getCurrentDateTimeUTC(),
                DateUtil.getCurrentDateTimeUTC(),
                "Test",
                "9999999999");
    }
}
