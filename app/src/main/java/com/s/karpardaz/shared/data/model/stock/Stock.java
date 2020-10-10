package com.s.karpardaz.shared.data.model.stock;

import static com.s.karpardaz.base.util.AppUtil.isAnyEmpty;

public class Stock {

    private String uuid;
    private String name;
    private String currency;
    private String currencyMark;
    private String createdAt;
    private String updatedAt;

    public Stock() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyMark() {
        return currencyMark;
    }

    public void setCurrencyMark(String currencyMark) {
        this.currencyMark = currencyMark;
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

    public void isAllNonNull() {
        if (isAnyEmpty(uuid, name, currency, currencyMark, createdAt, updatedAt))
            throw new NullPointerException();
    }
}
