package com.s.karpardaz.home;

import androidx.annotation.NonNull;

import com.s.karpardaz.shared.data.model.Cost;

public class CostHomeItem extends Cost {
    private int order;

    public CostHomeItem(long id, @NonNull String uuid, int order, @NonNull String purchasedAt,
            @NonNull String subject,
            @NonNull String amount,
            @NonNull String createdAt,
            @NonNull String updatedAt) {
        super(id, uuid, purchasedAt, subject, amount, createdAt, updatedAt);
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
