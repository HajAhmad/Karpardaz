package com.s.karpardaz.stock.model;

import com.s.karpardaz.base.model.BaseViewItem;
import com.s.karpardaz.stock.StockListAdapter;

public class StockListItem extends BaseViewItem {

    private int order;
    private String name;
    private String currency;
    private boolean isDefault;
    private final short type;

    public StockListItem(String id, int order, String name, String currency, boolean isDefault) {
        super(id);
        this.order = order;
        this.name = name;
        this.currency = currency;
        this.isDefault = isDefault;
        type = StockListAdapter.TYPE_NORMAL;
    }

    public StockListItem(short type) {
        this.type = type;
    }

    public short getType() {
        return type;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean isDefault() {
        return isDefault;
    }
}
