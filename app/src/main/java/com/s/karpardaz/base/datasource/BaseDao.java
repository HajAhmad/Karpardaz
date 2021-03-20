package com.s.karpardaz.base.datasource;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(T obj);

    @Update
    Integer update(T obj);

    @Delete
    Integer delete(T obj);
}
