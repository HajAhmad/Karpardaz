package com.s.karpardaz.shared.data.model.cost;

import androidx.annotation.NonNull;

public class Cost extends CostEntity {

    public Cost(@NonNull String uuid, @NonNull String purchasedAt,
            @NonNull String subject, @NonNull String amount, @NonNull String createdAt,
            @NonNull String updatedAt, boolean isBackedUp) {
        super(-1, uuid, purchasedAt, subject, amount, createdAt, updatedAt, isBackedUp);
    }

    public void setPurchasedAt(String purchasedAt) {
        this.purchasedAt = purchasedAt;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


}
