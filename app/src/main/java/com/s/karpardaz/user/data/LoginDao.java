package com.s.karpardaz.user.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.s.karpardaz.base.data.BaseDao;
import com.s.karpardaz.user.model.Login;

import io.reactivex.rxjava3.core.Maybe;

@Dao
public interface LoginDao extends BaseDao<Login> {

    @Query("select * from Login Limit 1")
    Maybe<Login> get();

    @Query("delete from login")
    void clearTable();


}
