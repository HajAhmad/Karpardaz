package com.s.karpardaz.cost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.s.karpardaz.R;
import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.base.ui.BaseDialogFragment;
import com.s.karpardaz.base.ui.BaseInteractionListener;
import com.s.karpardaz.cost.data.CostDataSource;
import com.s.karpardaz.cost.model.Cost;
import com.s.karpardaz.databinding.DialogCostDetailBinding;

import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.components.SingletonComponent;
import ir.huri.jcal.JalaliCalendar;

import static com.s.karpardaz.base.util.AppConstants.getDefaultStockId;
import static com.s.karpardaz.base.util.AppUtil.getCurrentDateTimeUTC;
import static com.s.karpardaz.base.util.AppUtil.isAnyEmpty;
import static com.s.karpardaz.base.util.view.AlertUtil.showToast;
import static java.util.Objects.requireNonNull;


@AndroidEntryPoint
public class CostDetailDialog extends BaseDialogFragment<CostDetailDialog.OnCostDetailDialogInteractionListener,
    DialogCostDetailBinding> implements CostDetailContract.View {

    @Inject
    CostDetailContract.Presenter mPresenter;

    public static final String TAG = CostDetailDialog.class.getSimpleName();

    private static final String COST_ID_KEY = "cost_id";
    private String mCostId;

    public static CostDetailDialog newInstance(@Nullable String costId) {
        CostDetailDialog fragment = new CostDetailDialog();

        if (!isAnyEmpty(costId)) {
            Bundle args = new Bundle();
            args.putString(COST_ID_KEY, costId);
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.takeView(this);
        if (getArguments() != null) {
            mCostId = getArguments().getString(COST_ID_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(DialogCostDetailBinding.inflate(inflater, container, false));
        return getBinding().getRoot();
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        getBinding().dialogCostDetailCloseAction.setOnClickListener(v -> dismiss());
        if (isAnyEmpty(mCostId)) initNewCost();
        else initCostUpdate();
    }

    private String getCurrentJalaliDate() {
        JalaliCalendar jc = new JalaliCalendar();
        return String.format(Locale.getDefault(), "%d/%d/%d",
            jc.getYear(),
            jc.getMonth(),
            jc.getDay());
    }

    private void initNewCost() {
        getBinding().dialogCostDetailPurchaseDate.setText(getCurrentJalaliDate());

        getBinding().dialogCostDetailSaveAction.setOnClickListener(v -> {
            Cost cost = getObject(null);
            if (cost != null) mPresenter.insertNewCost(cost);
        });
    }

    private void initCostUpdate() {
        mPresenter.showCost(mCostId);

        getBinding().dialogCostDetailSaveAction.setOnClickListener(v -> {
            Cost cost = getObject(mCostId);
            if (cost != null) mPresenter.updateCost(mCostId, cost);
        });
    }

    @Nullable
    private Cost getObject(@Nullable String id) {
        String title = getBinding().dialogCostDetailTitle.getText().toString();
        String amount = getBinding().dialogCostDetailPurchaseExpense.getText().toString();
        String description = getBinding().dialogCostDetailDescriptionInput.getText().toString();
        String atDate = getBinding().dialogCostDetailPurchaseDate.getText().toString();

        if (isAnyEmpty(title)) {
            showToast(getRoot(), R.string.empty_title_error);
            return null;
        } else if (isAnyEmpty(amount)) {
            showToast(getRoot(), R.string.empty_amount_error);
            return null;
        } else if (isAnyEmpty(description)) description = description.trim();

        return new Cost(id == null ? UUID.randomUUID().toString() : id,
            atDate,
            title.trim(),
            amount.trim(),
            description,
            getDefaultStockId(),
            getCurrentDateTimeUTC());
    }

    @Override
    public void showCostDetail(Cost cost) {
        getBinding().dialogCostDetailTitle.setText(cost.getSubject());
        getBinding().dialogCostDetailDescriptionInput.setText(cost.getDescription() == null ? "" :
            cost.getDescription());

        getBinding().dialogCostDetailPurchaseExpense.setText(cost.getAmount());
        getBinding().dialogCostDetailPurchaseDate.setText(cost.getAtDate());
    }

    @Override
    public void dismissAndUpdate() {
        getListener().updateList();
        dismiss();
    }

    @Override
    protected void clearReferences() {
        mPresenter.dropView();
        mPresenter = null;
        super.clearReferences();
    }

    interface OnCostDetailDialogInteractionListener extends BaseInteractionListener {
        void updateList();
    }


    static class CostDetailPresenter extends BasePresenter<CostDetailContract.View>
        implements CostDetailContract.Presenter {

        private final CostDataSource mCostRepository;

        @Inject
        CostDetailPresenter(@NonNull CostDataSource costDataSource) {
            mCostRepository = requireNonNull(costDataSource);
        }

        @Override
        public void showCost(String costId) {
            mCostRepository.get(costId, getView()::showCostDetail);
        }

        @Override
        public void insertNewCost(Cost cost) {
            requireNonNull(cost);
            mCostRepository.insert(cost, __ -> getView().dismissAndUpdate());
        }

        @Override
        public void updateCost(String costId, Cost cost) {
            if (isAnyEmpty(costId)) throw new NullPointerException();
            requireNonNull(cost);

            mCostRepository.update(costId, cost, __ -> getView().dismissAndUpdate());
        }
    }

    @Module
    @InstallIn(SingletonComponent.class)
    static abstract class CostDetailModule {
        @Binds
        abstract CostDetailContract.Presenter bindCostDetailPresenter(@NonNull CostDetailPresenter presenter);
    }

}
