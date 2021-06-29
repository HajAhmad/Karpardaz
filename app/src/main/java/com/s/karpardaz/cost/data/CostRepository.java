package com.s.karpardaz.cost.data;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.base.concurrent.AppExecutors;
import com.s.karpardaz.cost.model.Cost;

import java.util.List;

import javax.inject.Inject;

import static com.s.karpardaz.base.util.AppUtil.isAnyEmpty;
import static java.util.Objects.requireNonNull;

public class CostRepository implements CostDataSource {

    private final AppExecutors mExecutors;
    private final CostDao mDao;

    @Inject
    public CostRepository(@NonNull AppExecutors executors, @NonNull CostDao dao) {
        mDao = requireNonNull(dao);
        mExecutors = requireNonNull(executors);
    }

    @Override
    public void getAll(String stockId) {
        throw new NotImplementedException();
    }

    @Override
    public void getByDay(String stockId, @NonNull String date, BaseCallback<List<Cost>> baseCallback) {
        if (isAnyEmpty(stockId)) throw new NullPointerException();
        if (isAnyEmpty(date)) throw new NullPointerException();

        requireNonNull(baseCallback);

        mExecutors.getDiskIo().execute(() -> {
            List<Cost> list = mDao.getTodayLastByCount(stockId);
            mExecutors.getMainThread().execute(() -> baseCallback.onSuccess(list));
        });
    }

    @Override
    public void getByDate(String stockId, @NonNull String startDate, @NonNull String endDate) {
        throw new NotImplementedException();
    }

    @Override
    public void getBySubject(String stockId, @NonNull String subject) {
        throw new NotImplementedException();
    }

    @Override
    public void get(@NonNull String costId, BaseCallback<Cost> baseCallback) {
        if (isAnyEmpty()) throw new NotImplementedException();
        requireNonNull(baseCallback);

        mExecutors.getDiskIo().execute(() -> {
            Cost cost = mDao.get(costId);
            mExecutors.getMainThread().execute(() -> {
                if (cost == null) baseCallback.onFailure();
                else baseCallback.onSuccess(cost);
            });
        });
    }

    @Override
    public void insert(@NonNull Cost cost, BaseCallback<Void> callback) {
        requireNonNull(cost);
        requireNonNull(callback);

        mExecutors.getDiskIo().execute(() -> {
            long newInsertedId = mDao.insert(cost);
            mExecutors.getMainThread().execute(() -> {
                if (newInsertedId == -1) callback.onFailure();
                else callback.onSuccess();
            });
        });
    }

    @Override
    public void update(String costId, @NonNull Cost cost, BaseCallback<Void> callback) {
        if (isAnyEmpty(costId)) throw new NullPointerException();
        requireNonNull(cost);
        requireNonNull(callback);

        mExecutors.getDiskIo().execute(() -> {
            int count = mDao.update(costId, cost.getSubject(), cost.getAmount(), cost.getDescription());
            mExecutors.getMainThread().execute(() -> {
                if (count > 0) callback.onSuccess();
                else callback.onFailure();
            });
        });
    }

    @Override
    public void delete(String stockId, @NonNull Cost cost) {
        throw new NotImplementedException();
    }

    @Override
    public void refreshList() {
        throw new NotImplementedException();
    }
}
