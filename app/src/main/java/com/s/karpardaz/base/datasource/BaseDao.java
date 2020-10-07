package com.s.karpardaz.base.datasource;

public interface BaseDao<T> {

    long insert(T obj);

    int update(T obj);

    int delete(T obj);
}
