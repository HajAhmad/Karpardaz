package com.s.karpardaz.shared.data.model.cost;

import android.view.inspector.PropertyMapper;

import com.s.karpardaz.base.BaseMapperContract;

public class CostMapper implements BaseMapperContract<CostEntity, Cost, CostRemoteDto> {

    @Override
    public CostEntity parseLocalToEntity(Cost local) {
        return new CostEntity(-1, local.getUuid(),
                local.getPurchasedAt(),
                local.getSubject(),
                local.getAmount(),
                local.getCreatedAt(),
                local.getUpdatedAt(),
                local.isBackedUp());
    }

    @Override
    public Cost parseEntityToLocal(CostEntity entity) {
        return new Cost(entity.getUuid(),
                entity.getPurchasedAt(),
                entity.getSubject(),
                entity.getAmount(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isBackedUp());
    }

    @Override
    public CostRemoteDto parseLocalToRemote(Cost obj) {
        return new CostRemoteDto(
                obj.getUuid(),
                obj.getPurchasedAt(),
                obj.getSubject(),
                obj.getAmount(),
                obj.getCreatedAt(),
                obj.getUpdatedAt(),
                obj.isBackedUp()
        );
    }

    @Override
    public Cost parseRemoteToLocal(CostRemoteDto obj) {
        return null;
    }


}
