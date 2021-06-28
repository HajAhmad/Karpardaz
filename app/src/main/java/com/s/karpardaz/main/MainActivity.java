package com.s.karpardaz.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.s.karpardaz.R;
import com.s.karpardaz.base.ui.BaseActivity;
import com.s.karpardaz.cost.CostFragment;
import com.s.karpardaz.databinding.ActivityMainBinding;
import com.s.karpardaz.stock.StockFragment;
import com.s.karpardaz.user.EntryDialogFragment;
import com.s.karpardaz.user.ProfileFragment;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import ir.huri.jcal.JalaliCalendar;

import static com.s.karpardaz.base.util.view.AlertUtil.showToast;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainContract.View,
    CostFragment.OnCostListFragmentInteractionListener,
    EntryDialogFragment.OnEntryFragmentInteractionListener,
    ProfileFragment.OnProfileInteractionListener,
    StockFragment.OnStockFragmentInteractionListener {

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
        mPresenter.dropView();
        mPresenter = null;
        super.clearReferences();
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

        removeAllFragmentsIfExist();

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
            } else if (itemId == R.id.main_bottombar_report_item) {
                openReportFragment();
                return true;
            }
            return false;
        });

        mPresenter.checkForStocks();
    }

    @Override
    public void openStockFragment() {
        StockFragment fragment = StockFragment.newInstance();
        fragment.setInteractionListener(this);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.main_fragment_container, fragment, StockFragment.TAG)
            .commit();
    }

    @Override
    public void openProfileFragment() {
        ProfileFragment profileFragment = ProfileFragment.newInstance();
        profileFragment.setInteractionListener(this);
        profileFragment.show(getSupportFragmentManager(), ProfileFragment.TAG);
    }

    @Override
    public void openReportFragment() {
        Toast.makeText(this, "Not Implemented!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openCostFragment() {
        CostFragment fragment = CostFragment.newInstance();
        fragment.setInteractionListener(this);
        getSupportFragmentManager().beginTransaction().replace(
            R.id.main_fragment_container, fragment, CostFragment.TAG
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
        removeAllFragmentsIfExist();

        ProfileFragment fragment =
            (ProfileFragment) getSupportFragmentManager().findFragmentByTag(ProfileFragment.TAG);
        if (fragment != null)
            fragment.dismiss();

        showMessage(R.string.logout_message);
        showEntranceDialog();
    }

    private void removeAllFragmentsIfExist() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for (Fragment f : fragmentList) getSupportFragmentManager().beginTransaction().remove(f);
    }

    @Override
    public void showInsertStock() {
        int menuItemCount = getBinding().mainActivityBottombar.getMenu().size();
        for (int i = menuItemCount - 2; i >= 0; i--)
            getBinding().mainActivityBottombar.getMenu().getItem(i).setEnabled(false);

        getBinding().mainActivityBottombar.setSelectedItemId(R.id.main_bottombar_stock_item);
    }

    @Override
    public void enableAllMenuOptionsIfDisabled() {
        if (getBinding().mainActivityBottombar.getMenu().getItem(1).isEnabled()) return;

        int menuItemCount = getBinding().mainActivityBottombar.getMenu().size();
        for (int i = menuItemCount - 2; i >= 0; i--)
            getBinding().mainActivityBottombar.getMenu().getItem(i).setEnabled(true);

        getBinding().mainActivityBottombar.setSelectedItemId(R.id.main_bottombar_cost_item);
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
