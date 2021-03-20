package com.s.karpardaz.stock.data;

import androidx.room.Dao;

import com.s.karpardaz.base.datasource.BaseDao;
import com.s.karpardaz.stock.model.Stock;

@Dao
public interface StockDao extends BaseDao<Stock> {

}
