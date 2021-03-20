package com.s.karpardaz.cost.model;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.model.BaseFinViewEntity;

public class CostViewItem extends BaseFinViewEntity {

    public CostViewItem(int order, @NonNull String uuid, @NonNull String atDate,
            @NonNull String subject,
            @NonNull String amount,
            @NonNull String createdAt,
            @NonNull String updatedAt) {
        super(order, uuid, atDate, subject, amount, createdAt, updatedAt);
    }

}
