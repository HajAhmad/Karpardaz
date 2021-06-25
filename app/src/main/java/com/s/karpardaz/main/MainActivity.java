package com.s.karpardaz.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.s.karpardaz.R;
import com.s.karpardaz.base.ui.BaseActivity;
import com.s.karpardaz.base.util.AppConstants;
import com.s.karpardaz.cost.CostFragment;
import com.s.karpardaz.databinding.ActivityMainBinding;
import com.s.karpardaz.income.IncomeFragment;
import com.s.karpardaz.stock.StockFragment;
import com.s.karpardaz.user.EntryDialogFragment;
import com.s.karpardaz.user.ProfileFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import ir.huri.jcal.JalaliCalendar;

import static com.s.karpardaz.base.util.view.AlertUtil.showToast;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainContract.View,
    CostFragment.OnCostListFragmentInteractionListener,
    IncomeFragment.OnIncomeFragmentInteractionListener,
    EntryDialogFragment.OnEntryFragmentInteractionListener,
    ProfileFragment.OnProfileInteractionListener, StockFragment.OnStockFragmentInteractionListener {

    @Inject
    MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.takeView(this);
        setBinding(ActivityMainBinding.inflate(getLayoutInflater()));
        setContentView(getRoot());

        getBinding().mainToolbar.setVisibility(View.INVISIBLE);
        getBinding().mainActivityBottombar.setSelectedItemId(R.id.main_bottombar_cost_item);

        mPresenter.start();

    }

    @Override
    protected void clearReferences() {
        super.clearReferences();
        mPresenter.dropView();
        mPresenter = null;
    }

    @Override
    public void proceed() {
        DialogFragment entryFragment = (DialogFragment)
            getSupportFragmentManager().findFragmentByTag(EntryDialogFragment.TAG);
        if (entryFragment != null)
            entryFragment.dismiss();

        initViews();
    }

    private void initViews() {
        showMessage("PROCEEDED. USER ID = " + AppConstants.sActiveUserId);

        getBinding().mainToolbar.setVisibility(View.VISIBLE);
        getBinding().mainActivityBottombar.setSelectedItemId(R.id.main_bottombar_cost_item);
        getBinding().mainTodayDate.setText(new JalaliCalendar().getDayOfWeekDayMonthString());

        findViewById(R.id.main_action_profile).setOnClickListener(v -> openProfileFragment());

        getBinding().mainActivityBottombar.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.main_bottombar_stock_item) {
                openStockFragment();
                return true;
            } else if (itemId == R.id.main_bottombar_cost_item) {
                openCostFragment();
                return true;
            } else if (itemId == R.id.main_bottombar_income_item) {
                openIncomeFragment();
                return true;
            } else if (itemId == R.id.main_bottombar_report_item) {
                openReportFragment();
                return true;
            }
            return false;
        });

        mPresenter.checkForStocks();
    }

    private void openStockFragment() {
        StockFragment fragment = StockFragment.newInstance();
        fragment.setInteractionListener(this);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .commit();
    }

    private void openProfileFragment() {
        ProfileFragment profileFragment = ProfileFragment.newInstance();
        profileFragment.setInteractionListener(this);
        profileFragment.show(getSupportFragmentManager(), ProfileFragment.TAG);
    }

    private void openReportFragment() {
        Toast.makeText(this, "Not Implemented!", Toast.LENGTH_SHORT).show();
    }

    private void openCostFragment() {
        CostFragment fragment = CostFragment.newInstance();
        fragment.setInteractionListener(this);
        getSupportFragmentManager().beginTransaction().add(
            fragment, CostFragment.TAG
        ).commit();
    }

    private void openIncomeFragment() {
        IncomeFragment fragment = IncomeFragment.newInstance();
        fragment.setInteractionListener(this);
        getSupportFragmentManager().beginTransaction().add(
            fragment, IncomeFragment.TAG
        ).commit();
    }

    @Override
    public void showEntranceDialog() {
        final EntryDialogFragment entryDialog = EntryDialogFragment.newInstance();
        entryDialog.setInteractionListener(this);
        entryDialog.show(getSupportFragmentManager(),
            EntryDialogFragment.TAG);
    }

    @Override
    public void returnToLoginPage() {
        ProfileFragment fragment = (ProfileFragment) getSupportFragmentManager().findFragmentByTag(ProfileFragment.TAG);
        if (fragment != null)
            fragment.dismiss();
        showMessage(R.string.logout_message);
        showEntranceDialog();
    }

    @Override
    public void showInsertStock() {
        int menuItemCount = getBinding().mainActivityBottombar.getMenu().size();
        for (int i = 1; i < menuItemCount; i++)
            getBinding().mainActivityBottombar.getMenu().getItem(i).setEnabled(false);

        getBinding().mainActivityBottombar.setSelectedItemId(R.id.main_bottombar_stock_item);
    }

    @Override
    public void showCostFragment() {

    }

    @Override
    public void openAddCostDialog() {

    }

    @Override
    public void openAddIncomeDialog() {

    }


    @Override
    public void showMessage(String message) {
        showToast(getRoot(), message);
    }

    @Override
    public void showMessage(int stringRes) {
        showToast(getRoot(), stringRes);
    }

    @Override
    public void logout() {
        mPresenter.logout();
    }
}
