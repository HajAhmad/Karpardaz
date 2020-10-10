package com.s.karpardaz.home;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.s.karpardaz.base.mvvm.BaseViewModel;
import com.s.karpardaz.base.util.AppUtil;
import com.s.karpardaz.shared.data.model.cost.CostEntity;
import com.s.karpardaz.shared.data.source.CostDataSource;
import com.s.karpardaz.shared.data.source.CostRepository;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.s.karpardaz.base.util.AppUtil.strDateToDate;

public class CostListViewModel extends BaseViewModel {

    private LiveData<List<CostEntity>> costListObservable;
    public final MutableLiveData<String> weekTotalSum;
    private final CostRepository mRepository;

    @ViewModelInject
    public CostListViewModel(@NonNull CostRepository repository) {
        mRepository = Objects.requireNonNull(repository);

        costListObservable = new MutableLiveData<>();
        weekTotalSum = new MutableLiveData<>();

        loadLastCosts();
    }

    public LiveData<String> getTodayTotalCost(String str) {
        weekTotalSum.setValue(str);
        return weekTotalSum;
    }

    private void loadLastCosts() {
        String currentDateTime = AppUtil.getCurrentDateTimeUTC();
        try {
            String lastTwentyDaysDateTime = getLastTwentyDateTime(strDateToDate(currentDateTime));
            mRepository.getCostByDate(currentDateTime, lastTwentyDaysDateTime,
                    new CostDataSource.GetAllCostListCallback() {
                        @Override
                        public void onSuccess(LiveData<List<CostEntity>> result) {
                            costListObservable = result;
                        }

                        @Override
                        public <F extends Throwable> void onFailure(F failure) {

                        }
                    });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public LiveData<List<CostEntity>> getCostListObservable(){
        return costListObservable;
    }

    private String getLastTwentyDateTime(Date currentDateTime) {
        return new DateTime(currentDateTime)
                .minusDays(7)
                .toString(AppUtil.DEFAULT_DATE_PATTERN, AppUtil.DEFAULT_LOCALE);
    }

}
