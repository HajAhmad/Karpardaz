package com.s.karpardaz.base.datasource;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.s.karpardaz.shared.data.model.cost.CostEntity;
import com.s.karpardaz.shared.data.model.cost.CostDao;
import com.s.karpardaz.shared.data.model.stock.StockEntity;
import com.s.karpardaz.shared.data.model.stock.StockDao;

import static com.s.karpardaz.base.datasource.DbConstants.*;

@Database(entities = {
        CostEntity.class,
        StockEntity.class
},
        version = DB_VERSION)
public abstract class CostDatabase extends RoomDatabase {

    private static final Object sLock = new Object();

    private static CostDatabase sInstance;

    public static CostDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            CostDatabase.class, DB_NAME)
                            .build();
                }
            }
        }
        return sInstance;
    }

    public abstract CostDao costDao();
    public abstract StockDao stockDao();

}
