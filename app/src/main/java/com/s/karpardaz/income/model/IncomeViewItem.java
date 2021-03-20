package com.s.karpardaz.income.model;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.model.BaseFinViewEntity;

public class IncomeViewItem extends BaseFinViewEntity {
    public IncomeViewItem(int order, @NonNull String uuid,
            @NonNull String purchasedAt,
            @NonNull String subject,
            @NonNull String amount,
            @NonNull String createdAt,
            @NonNull String updatedAt) {
        super(order, uuid, purchasedAt, subject, amount, createdAt, updatedAt);
    }
}
