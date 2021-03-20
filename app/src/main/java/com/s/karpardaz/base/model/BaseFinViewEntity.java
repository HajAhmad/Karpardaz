package com.s.karpardaz.base.model;

import androidx.annotation.NonNull;

public abstract class BaseFinViewEntity extends BaseModel {

    private int order;
    private String purchasedAt;
    private String subject;
    private String amount;

    public BaseFinViewEntity(int order, @NonNull String uuid, @NonNull String purchasedAt,
            @NonNull String subject,
            @NonNull String amount,
            @NonNull String createdAt,
            @NonNull String updatedAt) {
        super(uuid, createdAt, updatedAt);
        this.order = order;
        this.purchasedAt = purchasedAt;
        this.subject = subject;
        this.amount = amount;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPurchasedAt() {
        return purchasedAt;
    }

    public void setPurchasedAt(String purchasedAt) {
        this.purchasedAt = purchasedAt;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}