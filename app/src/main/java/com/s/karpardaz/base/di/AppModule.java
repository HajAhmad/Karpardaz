package com.s.karpardaz.base.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.s.karpardaz.base.concurrent.AppExecutors;
import com.s.karpardaz.base.datasource.KarpardazDatabase;
import com.s.karpardaz.base.datasource.SharedPrefDataSource;
import com.s.karpardaz.base.datasource.SharedPrefHelper;

import java.util.Objects;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import static com.s.karpardaz.base.datasource.KarpardazDatabase.DB_NAME;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Binds
    @Singleton
    public abstract SharedPrefDataSource bindSharedPrefDataSource(@NonNull SharedPrefHelper sharedPrefHelper);

    @Provides
    @Singleton
    public static KarpardazDatabase provideDatabase(@NonNull Application application) {
        Objects.requireNonNull(application);

        final RoomDatabase.Builder<KarpardazDatabase> dbBuilder = Room.databaseBuilder(
            application.getApplicationContext(), KarpardazDatabase.class, DB_NAME)
            .fallbackToDestructiveMigration();

        //        if (BuildConfig.DEBUG) {
        //            dbBuilder.addCallback(new RoomDatabase.Callback() {
        //                @Override
        //                public void onCreate(@NonNull SupportSQLiteDatabase db) {
        //                    super.onCreate(db);
        //                    Executors.newSingleThreadExecutor().execute(() -> {
        //                        ContentValues values = new ContentValues();
        //                        values.put("userId", AppConstants.EMPTY_UUID);
        //                        values.put("createdAt", AppUtil.getCurrentDateTimeUTC());
        //                        db.insert(Login.class.getSimpleName(),
        //                                SQLiteDatabase.CONFLICT_IGNORE, values);
        //                        Log.d(this.getClass().getSimpleName(), "onCreate: Default User Added.");
        //                    });
        //                }
        //            });
        //        }

        return dbBuilder.build();
    }

    @Provides
    @Singleton
    public static AppExecutors provideExecutors() {
        return new AppExecutors();
    }


}
