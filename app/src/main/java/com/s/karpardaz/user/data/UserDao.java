package com.s.karpardaz.user.data;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.s.karpardaz.base.dao.BaseDao;
import com.s.karpardaz.base.model.User;

@Dao
public interface UserDao extends BaseDao<User> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(User obj);

    @Delete
    Integer delete(User obj);

    @Update
    Integer update(User obj);

    @Query("select * from User where id = :id")
    User get(Long id);

    @Query("select * from User where uuid = :userId")
    User get(String userId);

    @Query("select * from User where email = :email and password = :password")
    User get(String email, String password);

    @Query("select uuid from User where id = :id")
    String getInsertedId(long id);
}
