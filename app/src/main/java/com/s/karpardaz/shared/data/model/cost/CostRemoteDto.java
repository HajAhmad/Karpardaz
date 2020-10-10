package com.s.karpardaz.shared.data.model.cost;

import androidx.annotation.NonNull;

public class CostRemoteDto extends Cost {

    public CostRemoteDto(@NonNull String uuid,
            @NonNull String purchasedAt,
            @NonNull String subject,
            @NonNull String amount,
            @NonNull String createdAt,
            @NonNull String updatedAt, boolean isBackedUp) {
        super(uuid, purchasedAt, subject, amount, createdAt, updatedAt, isBackedUp);
    }

}
