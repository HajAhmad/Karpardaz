package com.s.karpardaz.stock.data;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.concurrent.AppExecutors;
import com.s.karpardaz.base.util.AppUtil;
import com.s.karpardaz.stock.model.Stock;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import retrofit2.internal.EverythingIsNonNull;

@EverythingIsNonNull
public class StockRepository implements StockDataSource {

    private final StockDao mDao;
    private final AppExecutors mExecutors;

    @Inject
    public StockRepository(@NonNull StockDao dao, @NonNull AppExecutors executors) {
        this.mDao = Objects.requireNonNull(dao);
        this.mExecutors = Objects.requireNonNull(executors);
    }

    @Override
    public void isThereAnyStock(@NonNull String userId, @NonNull IsAnyCallback callback) {
        if (AppUtil.isAnyEmpty(userId)) throw new NullPointerException();
        Objects.requireNonNull(callback);

        mExecutors.getDiskIo().execute(() -> {
            if (isAnyLocal()) mExecutors.getMainThread().execute(callback::onSuccess);
            else mExecutors.getMainThread().execute(callback::no);
        });
    }

    @Override
    public void getAllStocks(@NonNull String userId, @NonNull BaseCallback<List<Stock>> listBaseCallback) {
        mExecutors.getDiskIo().execute(() -> {
            List<Stock> all = mDao.getAll();
            mExecutors.getMainThread().execute(() -> listBaseCallback.onSuccess(all));
        });
    }


    private boolean isAnyLocal() {
        return mDao.isAny() == 1;
    }

}
