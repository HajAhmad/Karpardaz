package com.s.karpardaz.home;

import androidx.annotation.NonNull;

public class CostHomeItem {

    private int order;
    private String uuid;
    private String purchasedAt;
    private String subject;
    private String amount;
    private String updatedAt;

    public CostHomeItem(int order, @NonNull String uuid, @NonNull String purchasedAt,
            @NonNull String subject,
            @NonNull String amount,
            @NonNull String updatedAt) {
        this.order = order;
        this.uuid = uuid;
        this.purchasedAt = purchasedAt;
        this.subject = subject;
        this.amount = amount;
        this.updatedAt = updatedAt;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
