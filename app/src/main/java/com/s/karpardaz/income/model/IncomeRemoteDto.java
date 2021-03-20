package com.s.karpardaz.income.model;

import com.s.karpardaz.base.model.FinRemoteDto;

public class IncomeRemoteDto extends FinRemoteDto {
    public IncomeRemoteDto(String uuid, String purchasedAt, String subject, String amount,
            String createdAt, String updatedAt) {
        super(uuid, purchasedAt, subject, amount, createdAt, updatedAt);
    }
}
