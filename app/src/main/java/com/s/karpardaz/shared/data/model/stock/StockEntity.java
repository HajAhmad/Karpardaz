package com.s.karpardaz.shared.data.model.stock;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.s.karpardaz.base.BaseModel;

import java.util.Objects;

@Entity(tableName = "Stock")
public class StockEntity extends BaseModel {

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

    public StockEntity(long id, @NonNull String uuid, @NonNull String name, @NonNull String currency,
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
        StockEntity EStock = (StockEntity) o;
        return id == EStock.id &&
                uuid.equals(EStock.uuid) &&
                name.equals(EStock.name) &&
                currency.equals(EStock.currency) &&
                currencyMark.equals(EStock.currencyMark) &&
                createdAt.equals(EStock.createdAt) &&
                updatedAt.equals(EStock.updatedAt);
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
