package com.s.karpardaz.shared.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.s.karpardaz.base.BaseModel;

import java.util.Objects;

@Entity
public class Stock extends BaseModel {

    @NonNull
    private String name;
    @NonNull
    private String currency;
    @NonNull
    private String currencyMark;
    @NonNull
    private String createdAt;
    @NonNull
    private String updatedAt;

    public Stock(long id, @NonNull String uuid, @NonNull String name, @NonNull String currency,
            @NonNull String currencyMark, @NonNull String createdAt,
            @NonNull String updatedAt) {
        super(id, uuid);
        this.name = name;
        this.currency = currency;
        this.currencyMark = currencyMark;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getCurrency() {
        return currency;
    }

    @NonNull
    public String getCurrencyMark() {
        return currencyMark;
    }

    @NonNull
    public String getCreatedAt() {
        return createdAt;
    }

    @NonNull
    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Stock stock = (Stock) o;
        return id == stock.id &&
                uuid.equals(stock.uuid) &&
                name.equals(stock.name) &&
                currency.equals(stock.currency) &&
                currencyMark.equals(stock.currencyMark) &&
                createdAt.equals(stock.createdAt) &&
                updatedAt.equals(stock.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, currency, currencyMark, createdAt, updatedAt);
    }

    @NonNull
    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", currencyMark='" + currencyMark + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                "," + super.toString() +
                '}';
    }
}
