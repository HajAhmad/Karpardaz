package com.s.karpardaz.base.model;

import androidx.annotation.NonNull;

public abstract class FinRemoteDto extends BaseModel {

    private String uuid;
    private String purchasedAt;
    private String subject;
    private String amount;
    private String createdAt;
    private String updatedAt;

    public FinRemoteDto(String uuid, String purchasedAt, String subject, String amount,
            String createdAt, String updatedAt) {
        super(uuid, createdAt, updatedAt);
        this.uuid = uuid;
        this.purchasedAt = purchasedAt;
        this.subject = subject;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public String getPurchasedAt() {
        return purchasedAt;
    }

    public String getSubject() {
        return subject;
    }

    public String getAmount() {
        return amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @NonNull
    @Override
    public String toString() {
        return "FinRemoteDto{" +
                "uuid='" + uuid + '\'' +
                ", purchasedAt='" + purchasedAt + '\'' +
                ", subject='" + subject + '\'' +
                ", amount='" + amount + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                "} " + super.toString();
    }
}