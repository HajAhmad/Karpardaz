package com.s.karpardaz.base.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public abstract class BaseFin extends BaseEntity {

    @NonNull
    protected String atDate;
    @NonNull
    protected String subject;
    @NonNull
    protected String amount;
    @NonNull
    protected String stockId;

    protected BaseFin(long id,
            @NonNull String uuid,
            @NonNull String atDate,
            @NonNull String subject,
            @NonNull String amount,
            @NonNull String stockId,
            @NonNull String createdAt,
            @NonNull String updatedAt) {
        super(id, uuid, createdAt, updatedAt);
        this.subject = subject;
        this.amount = amount;
        this.atDate = atDate;
        this.stockId = stockId;
    }

    @NonNull
    public String getAtDate() {
        return atDate;
    }

    @NonNull
    public String getSubject() {
        return subject;
    }

    @NonNull
    public String getAmount() {
        return amount;
    }

    public void setAtDate(@NonNull String atDate) {
        this.atDate = atDate;
    }

    public void setSubject(@NonNull String subject) {
        this.subject = subject;
    }

    public void setAmount(@NonNull String amount) {
        this.amount = amount;
    }

    @NonNull
    public String getStockId() {
        return stockId;
    }

    public void setStockId(@NonNull String stockId) {
        this.stockId = stockId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseFin)) return false;
        if (!super.equals(o)) return false;
        BaseFin baseFin = (BaseFin) o;
        return getAtDate().equals(baseFin.getAtDate()) &&
                getSubject().equals(baseFin.getSubject()) &&
                getAmount().equals(baseFin.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAtDate(), getSubject(), getAmount());
    }

    @NonNull
    @Override
    public String toString() {
        return "BaseFin{" +
                "atDate='" + atDate + '\'' +
                ", subject='" + subject + '\'' +
                ", amount='" + amount + '\'' +
                ", stockId='" + stockId + '\'' +
                ", id=" + id +
                ", uuid='" + uuid + '\'' +
                "} " + super.toString();
    }
}
