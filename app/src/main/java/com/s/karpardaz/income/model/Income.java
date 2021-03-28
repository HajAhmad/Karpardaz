package com.s.karpardaz.income.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;

import com.s.karpardaz.base.model.BaseEntity;

import java.util.Objects;

@Entity(indices = {@Index(value = {"uuid", "id"}, unique = true)})
public class Income extends BaseEntity {

    @NonNull
    protected String atDate;
    @NonNull
    protected String subject;
    @NonNull
    protected String amount;
    @NonNull
    protected String stockId;

    public Income(long id, @NonNull String uuid, @NonNull String atDate, @NonNull String subject,
            @NonNull String amount, @NonNull String stockId, @NonNull String createdAt,
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
        if (!(o instanceof Income)) return false;
        if (!super.equals(o)) return false;
        Income income = (Income) o;
        return getAtDate().equals(income.getAtDate()) &&
                getSubject().equals(income.getSubject()) &&
                getAmount().equals(income.getAmount()) &&
                getStockId().equals(income.getStockId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAtDate(), getSubject(), getAmount(), getStockId());
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
