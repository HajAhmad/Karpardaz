package com.s.karpardaz.stock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.s.karpardaz.R;
import com.s.karpardaz.base.ui.BaseDialogFragment;
import com.s.karpardaz.base.ui.BaseInteractionListener;
import com.s.karpardaz.base.util.view.AlertUtil;
import com.s.karpardaz.databinding.DialogAddStockBinding;
import com.s.karpardaz.stock.model.Stock;

import java.util.UUID;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.s.karpardaz.base.util.AppUtil.getCurrentDateTimeUTC;
import static com.s.karpardaz.base.util.AppUtil.isAnyEmpty;
import static com.s.karpardaz.base.util.view.AlertUtil.showLongToast;
import static com.s.karpardaz.base.util.view.AlertUtil.showToast;

@AndroidEntryPoint
public class StockDetailDialog extends BaseDialogFragment<StockDetailDialog.StockDetailInteractionListener, DialogAddStockBinding>
    implements StockDetailContract.View {

    public static final String TAG_NEW = "stock_detail_new";
    public static final String TAG_UPDATE = "stock_detail_update";

    private static final String STOCK_ID_KEY = "stock_id_key";
    private String mStockId;

    @Inject
    StockDetailContract.Presenter mPresenter;

    public static StockDetailDialog newInstanceForInsertion() {
        return newInstanceForUpdate(null);
    }

    public static StockDetailDialog newInstanceForUpdate(String stockUuid) {
        StockDetailDialog fragment = new StockDetailDialog();

        if (stockUuid != null) {
            Bundle args = new Bundle();
            args.putString(STOCK_ID_KEY, stockUuid);
            fragment.setArguments(args);

        }

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter.takeView(this);
        setBinding(DialogAddStockBinding.inflate(inflater, container, false));
        return getBinding().getRoot();
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null)
            mStockId = getArguments().getString(STOCK_ID_KEY);

        getBinding().dialogAddStockCloseAction.setOnClickListener(v -> dismiss());

        if (mStockId == null) initInsertion();
        else initStockUpdate();


    }

    private void initInsertion() {
        getBinding().dialogAddStockTitle.setText(R.string.add);
        getBinding().dialogAddStockSave.setOnClickListener(v -> addNewStock());
        mPresenter.disableDefaultCheck();
    }

    private void initStockUpdate() {
        getBinding().dialogAddStockTitle.setText(R.string.update);
        mPresenter.getStockDetail(mStockId);
        getBinding().dialogAddStockSave.setOnClickListener(v -> updateStock());
    }

    private void updateStock() {
        Stock stock = getObject();
        if (stock != null) {
            boolean isDefault = getBinding().dialogAddStockIsDefaultCheck.isChecked();
            mPresenter.updateStock(stock, isDefault);
        }
    }

    private void addNewStock() {
        Stock stock = getObject();
        if (stock != null) {
            boolean isDefault = getBinding().dialogAddStockIsDefaultCheck.isChecked();
            mPresenter.addStock(stock, isDefault);
        }
    }


    @Nullable
    private Stock getObject() {
        String currency = getBinding().dialogAddStockCurrencyInput.getText().toString();
        String name = getBinding().dialogAddStockNameInput.getText().toString();

        if (isAnyEmpty(name)) {
            AlertUtil.showToast(getRoot(), R.string.must_have_a_name);
            return null;
        } else if (isAnyEmpty(currency)) {
            AlertUtil.showToast(getRoot(), R.string.must_have_a_currency);
            return null;
        }

        return new Stock(UUID.randomUUID().toString(), getCurrentDateTimeUTC(), name, currency);
    }

    @Override
    protected void clearReferences() {
        super.clearReferences();
        mPresenter = null;
    }


    @Override
    public void showStock(@NonNull Stock result) {
        getBinding().dialogAddStockNameInput.setText(result.getName());
        getBinding().dialogAddStockCurrencyInput.setText(result.getCurrency());
    }

    @Override
    public void insertionFailed() {
        showToast(getRoot(), R.string.unknown_error);
    }

    @Override
    public void newStockInserted() {
        showToast(getRoot(), R.string.stock_stock_created_message);
        getListener().updateStockList();
        dismiss();
    }

    @Override
    public void showStockFoundError() {
        showLongToast(getRoot(), R.string.stock_stock_found_error_message);
    }

    @Override
    public void showStockNotFoundError() {
        showToast(getRoot(), R.string.stock_not_found);
    }

    @Override
    public void showSetDefaultStockFailed() {
        showToast(getRoot(), R.string.set_default_stock_failed);
    }

    @Override
    public void setDefaultStock() {
        getBinding().dialogAddStockIsDefaultCheck.setChecked(true);
        getBinding().dialogAddStockIsDefaultCheck.setEnabled(false);
    }

    @Override
    public void setNotDefaultStock() {
        getBinding().dialogAddStockIsDefaultCheck.setChecked(false);
    }

    @Override
    public void showStockUpdated() {
        showToast(getRoot(), R.string.stock_updated);
        getListener().updateStockList();
        dismiss();
    }

    @Override
    public void disableDefaultOption() {
        getBinding().dialogAddStockIsDefaultCheck.setEnabled(false);
    }

    public interface StockDetailInteractionListener extends BaseInteractionListener {
        void updateStockList();
    }
}
