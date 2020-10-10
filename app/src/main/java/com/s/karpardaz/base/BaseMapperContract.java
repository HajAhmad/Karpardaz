package com.s.karpardaz.base;

public interface BaseMapperContract<EntityDto, LocalDto, RemoteDto> {
    EntityDto parseLocalToEntity(LocalDto obj);
    LocalDto parseEntityToLocal(EntityDto obj);
    RemoteDto parseLocalToRemote(LocalDto obj);
    LocalDto parseRemoteToLocal(RemoteDto obj);
}
