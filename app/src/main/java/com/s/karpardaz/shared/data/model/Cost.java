package com.s.karpardaz.shared.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.s.karpardaz.base.BaseModel;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Entity
public class Cost extends BaseModel {

    @NonNull
    private String purchasedAt;
    @NonNull
    private String subject;
    @NonNull
    private String amount;

    @NonNull
    private final String createdAt;
    @NonNull
    private String updatedAt;

    public Cost(long id,
            @NonNull String uuid,
            @NonNull String purchasedAt,
            @NonNull String subject,
            @NonNull String amount,
            @NonNull String createdAt,
            @NonNull String updatedAt) {
        super(id, uuid);
        this.subject = requireNonNull(subject);
        this.amount = requireNonNull(amount);
        this.purchasedAt = requireNonNull(purchasedAt);
        this.createdAt = requireNonNull(createdAt);
        this.updatedAt = requireNonNull(updatedAt);
    }

    @NonNull
    public String getPurchasedAt() {
        return purchasedAt;
    }

    @NonNull
    public String getSubject() {
        return subject;
    }

    @NonNull
    public String getAmount() {
        return amount;
    }

    @NonNull
    public String getCreatedAt() {
        return createdAt;
    }

    @NonNull
    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setPurchasedAt(@NonNull String purchasedAt) {
        this.purchasedAt = purchasedAt;
    }

    public void setSubject(@NonNull String subject) {
        this.subject = subject;
    }

    public void setAmount(@NonNull String amount) {
        this.amount = amount;
    }

    public void setUpdatedAt(@NonNull String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cost cost = (Cost) o;
        return purchasedAt.equals(cost.purchasedAt) &&
                subject.equals(cost.subject) &&
                amount.equals(cost.amount) &&
                createdAt.equals(cost.createdAt) &&
                updatedAt.equals(cost.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), purchasedAt, subject, amount, createdAt, updatedAt);
    }

    @NonNull
    @Override
    public String toString() {
        return "Cost{" +
                "purchasedAt='" + purchasedAt + '\'' +
                ", subject='" + subject + '\'' +
                ", amount='" + amount + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                "," + super.toString() +
                '}';
    }
}
