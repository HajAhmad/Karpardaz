package com.s.karpardaz.shared.data.model.stock;

public class StockRemote {

    private String uuid;
    private String name;
    private String currency;
    private String currencyMark;
    private String createdAt;
    private String updatedAt;

    public StockRemote(String uuid, String name, String currency, String currencyMark,
            String createdAt, String updatedAt) {
        this.uuid = uuid;
        this.name = name;
        this.currency = currency;
        this.currencyMark = currencyMark;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCurrencyMark() {
        return currencyMark;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
