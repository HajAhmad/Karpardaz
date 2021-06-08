package com.s.karpardaz.user.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.s.karpardaz.base.data.BaseDao;
import com.s.karpardaz.base.model.User;

@Dao
public interface UserDao extends BaseDao<User> {

    @Query("select * from User where id = :id")
    User get(Long id);

    @Query("select * from User where uuid = :userId")
    User get(String userId);

    @Query("select * from User where email = :email and password = :password")
    User get(String email, String password);

    @Query("select uuid from User where id = :id")
    String getInsertedId(long id);
}
