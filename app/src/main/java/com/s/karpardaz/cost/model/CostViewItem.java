package com.s.karpardaz.cost.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.s.karpardaz.base.model.BaseViewItem;

public class CostViewItem extends BaseViewItem {
    private int order;
    private String purchasedAt;
    private String subject;
    private String amount;
    private String createdAt;
    private String updatedAt;

    public CostViewItem(int order, @NonNull String uuid, @NonNull String purchasedAt,
        @NonNull String subject,
        @NonNull String amount,
        @NonNull String createdAt,
        @Nullable String updatedAt) {

        super(uuid);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.order = order;
        this.purchasedAt = purchasedAt;
        this.subject = subject;
        this.amount = amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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
