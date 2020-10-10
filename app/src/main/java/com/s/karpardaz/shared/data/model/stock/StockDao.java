package com.s.karpardaz.shared.data.model.stock;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.s.karpardaz.base.datasource.BaseDao;

@Dao
public interface StockDao extends BaseDao<StockEntity> {

    @Insert
    @Override
    long insert(StockEntity obj);

    @Update
    @Override
    int update(StockEntity obj);

    @Delete
    @Override
    int delete(StockEntity obj);
}
