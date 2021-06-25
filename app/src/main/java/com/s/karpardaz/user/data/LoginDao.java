package com.s.karpardaz.user.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.s.karpardaz.base.dao.BaseDao;
import com.s.karpardaz.base.model.Login;

@Dao
public interface LoginDao extends BaseDao<Login> {

    @Query("select * from Login Limit 1")
    Login get();

    @Query("delete from Login")
    void clearTable();


}
