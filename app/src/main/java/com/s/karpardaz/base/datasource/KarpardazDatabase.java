package com.s.karpardaz.base.datasource;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.s.karpardaz.cost.data.CostDao;
import com.s.karpardaz.cost.model.Cost;
import com.s.karpardaz.income.data.IncomeDao;
import com.s.karpardaz.income.model.Income;
import com.s.karpardaz.stock.data.StockDao;
import com.s.karpardaz.stock.model.Stock;
import com.s.karpardaz.user.data.local.LoginDao;
import com.s.karpardaz.user.data.local.UserDao;
import com.s.karpardaz.user.model.Login;
import com.s.karpardaz.user.model.User;

import static com.s.karpardaz.base.datasource.KarpardazDatabase.DB_VERSION;

@Database(entities = {
        Cost.class,
        Income.class,
        Stock.class,
        User.class,
        Login.class
},
        version = DB_VERSION)
public abstract class KarpardazDatabase extends RoomDatabase {

    static final int DB_VERSION = 4;
    public static final String DB_NAME = "cost.db";

    public abstract CostDao costDao();
    public abstract IncomeDao incomeDao();
    public abstract StockDao stockDao();
    public abstract UserDao userDao();
    public abstract LoginDao loginDao();

}
