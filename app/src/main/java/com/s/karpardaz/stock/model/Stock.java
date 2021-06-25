package com.s.karpardaz.stock.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.s.karpardaz.base.model.BaseEntity;

import java.util.Objects;

@Entity(tableName = "Stock")
public class Stock extends BaseEntity {

    @NonNull
    private String name;
    @NonNull
    private String currency;
    @NonNull
    private String currencyMark;

    public Stock(long id, @NonNull String uuid, @NonNull String name,
            @NonNull String currency, @NonNull String currencyMark, @NonNull String createdAt,
            @NonNull String updatedAt) {
        super(id, uuid, createdAt, updatedAt);
        this.name = name;
        this.currency = currency;
        this.currencyMark = currencyMark;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Stock that = (Stock) o;
        return name.equals(that.name) &&
                currency.equals(that.currency) &&
                currencyMark.equals(that.currencyMark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, currency, currencyMark);
    }

    @NonNull
    @Override
    public String toString() {
        return "StockEntity{" +
                "id=" + getId() +
                ", uuid='" + getUuid() + '\'' +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", currencyMark='" + currencyMark + '\'' +
                '}';
    }
}
