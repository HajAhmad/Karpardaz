package com.s.karpardaz.user.data.local;

import androidx.room.Dao;
import androidx.room.Query;

import com.s.karpardaz.base.datasource.BaseDao;
import com.s.karpardaz.user.model.Login;

@Dao
public interface LoginDao extends BaseDao<Login> {

    @Query("select * from Login Limit 1")
    Login get();

    @Query("delete from login")
    void clearTable();


}
