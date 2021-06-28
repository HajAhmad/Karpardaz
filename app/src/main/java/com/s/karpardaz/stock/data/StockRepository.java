package com.s.karpardaz.stock.data;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.concurrent.AppExecutors;
import com.s.karpardaz.stock.model.DefaultStock;
import com.s.karpardaz.stock.model.Stock;

import java.util.List;

import javax.inject.Inject;

import retrofit2.internal.EverythingIsNonNull;

import static com.s.karpardaz.base.util.AppUtil.isAnyEmpty;
import static java.util.Objects.requireNonNull;

@EverythingIsNonNull
public class StockRepository implements StockDataSource {

    private final StockDao mDao;
    private final DefaultStockDao mDefaultStockDao;
    private final AppExecutors mExecutors;

    @Inject
    public StockRepository(@NonNull StockDao dao, @NonNull DefaultStockDao defaultStockDao,
        @NonNull AppExecutors executors) {
        this.mDao = requireNonNull(dao);
        this.mDefaultStockDao = requireNonNull(defaultStockDao);
        this.mExecutors = requireNonNull(executors);
    }

    @Override
    public void isThereAnyStock(@NonNull String userId, @NonNull IsAnyCallback callback) {
        if (isAnyEmpty(userId)) throw new NullPointerException();
        requireNonNull(callback);

        mExecutors.getDiskIo().execute(() -> {
            int count = mDao.getTableCount();
            mExecutors.getMainThread().execute(() -> {
                if (count > 0) callback.yes();
                else callback.no();
            });
        });
    }

    @Override
    public void getAllStocks(@NonNull String userId, @NonNull BaseCallback<List<Stock>> listBaseCallback) {
        if (isAnyEmpty(userId)) throw new NullPointerException();
        requireNonNull(listBaseCallback);

        mExecutors.getDiskIo().execute(() -> {
            List<Stock> all = mDao.getAll();
            mExecutors.getMainThread().execute(() -> listBaseCallback.onSuccess(all));
        });
    }

    @Override
    public void addStock(Stock stock, BaseCallback<Void> callback) {
        requireNonNull(stock);
        requireNonNull(callback);

        mExecutors.getDiskIo().execute(() -> {
            long returnedId = mDao.insert(stock);
            mExecutors.getMainThread().execute(() -> {
                if (returnedId == -1) callback.onFailure();
                else callback.onSuccess();
            });
        });
    }

    @Override
    public void isStockUnique(@NonNull String stockName, @NonNull IsStockUniqueCallback callback) {
        requireNonNull(stockName);
        requireNonNull(callback);

        mExecutors.getDiskIo().execute(() -> {
            int count = mDao.getSameNameCount(stockName);
            mExecutors.getMainThread().execute(() -> {
                if (count == 0) callback.yes();
                else callback.no();
            });
        });
    }

    @Override
    public void getStock(@NonNull String stockId, @NonNull GetStockCallback callback) {
        if (isAnyEmpty(stockId)) throw new NullPointerException();
        requireNonNull(callback);

        mExecutors.getDiskIo().execute(() -> {
            Stock stock = mDao.get(stockId);
            mExecutors.getMainThread().execute(() -> {
                if (stock == null) callback.stockNotFound();
                else callback.onSuccess(stock);
            });
        });
    }

    @Override
    public void setDefaultStock(@NonNull String stockUuid, @NonNull String createdAt, @NonNull BaseCallback<Void> callback) {
        if (isAnyEmpty(stockUuid)) throw new NullPointerException();
        requireNonNull(callback);

        mExecutors.getDiskIo().execute(() -> {
            mDefaultStockDao.clearTable();
            long returnedId = mDefaultStockDao.insert(new DefaultStock(stockUuid, createdAt));
            mExecutors.getMainThread().execute(() -> {
                if (returnedId == -1) callback.onFailure();
                else callback.onSuccess();
            });
        });
    }

    @Override
    public void isStockDefault(@NonNull String stockId, @NonNull IsStockDefaultCallback callback) {
        if (isAnyEmpty(stockId)) throw new NullPointerException();
        requireNonNull(callback);

        mExecutors.getDiskIo().execute(() -> {
            int i = mDefaultStockDao.isStockDefault(stockId);
            mExecutors.getMainThread().execute(() -> {
                if (i == 0) callback.no();
                else callback.yes();
            });
        });
    }

    @Override
    public void updateStock(Stock stock, BaseCallback<Void> callback) {
        requireNonNull(stock);
        requireNonNull(callback);

        mExecutors.getDiskIo().execute(() -> {
            int i = mDao.update(stock.getUuid(), stock.getName(), stock.getCurrency(), stock.getUpdatedAt());
            mExecutors.getMainThread().execute(() -> {
                if (i == 0) callback.onFailure();
                else callback.onSuccess();
            });
        });
    }

    @Override
    public void getDefaultStockId(@NonNull BaseCallback<DefaultStock> callback) {
        requireNonNull(callback);

        mExecutors.getDiskIo().execute(() -> {
            DefaultStock defStock = mDefaultStockDao.get();
            mExecutors.getMainThread().execute(() -> {
                if (defStock == null) callback.onFailure(new Resources.NotFoundException());
                else callback.onSuccess(requireNonNull(defStock));
            });
        });
    }

    @Override
    public void isDefaultStockTableEmpty(@NonNull IsTableEmptyCallback callback) {
        requireNonNull(callback);

        mExecutors.getDiskIo().execute(() -> {
            Integer count = mDefaultStockDao.getTableCount();
            mExecutors.getMainThread().execute(() -> {
                if (count == 0) callback.yes();
                else callback.no();
            });
        });
    }

}
