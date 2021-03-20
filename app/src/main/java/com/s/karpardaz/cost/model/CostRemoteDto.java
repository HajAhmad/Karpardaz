package com.s.karpardaz.cost.model;


import com.s.karpardaz.base.model.FinRemoteDto;

public class CostRemoteDto extends FinRemoteDto {

    public CostRemoteDto(String uuid, String purchasedAt, String subject, String amount,
            String createdAt, String updatedAt) {
        super(uuid, purchasedAt, subject, amount, createdAt, updatedAt);
    }

}
