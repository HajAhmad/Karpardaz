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
import com.s.karpardaz.user.EntryDialogFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import ir.huri.jcal.JalaliCalendar;

import static com.s.karpardaz.base.util.view.SnackbarUtil.showSnackbar;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainContract.View,
    CostFragment.OnCostListFragmentInteractionListener,
    IncomeFragment.OnIncomeFragmentInteractionListener,
    EntryDialogFragment.OnEntryFragmentInteractionListener {

    @Inject
    MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.takeView(this);
        setBinding(ActivityMainBinding.inflate(getLayoutInflater()));
        setContentView(getRoot());

        getBinding().mainToolbar.setVisibility(View.INVISIBLE);

        mPresenter.isUserAvailable();

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

        showMessage("PROCEEDED. USER ID = " + AppConstants.sActiveUserId);

        getBinding().mainToolbar.setVisibility(View.VISIBLE);
        getBinding().mainActivityBottombar.setSelectedItemId(R.id.main_bottombar_cost_item);

        getBinding().mainTodayDate
            .setText(new JalaliCalendar().getDayOfWeekDayMonthString());

        findViewById(R.id.main_action_profile).setOnClickListener(v -> openProfileFragment());

        getBinding().mainActivityBottombar.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.main_bottombar_cost_item) {
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
    }


    private void openProfileFragment() {
        Toast.makeText(this, "Not Implemented!", Toast.LENGTH_SHORT).show();
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
        EntryDialogFragment.newInstance().show(getSupportFragmentManager(),
            EntryDialogFragment.TAG);
    }

    @Override
    public void openAddCostDialog() {

    }

    @Override
    public void openAddIncomeDialog() {

    }


    @Override
    public void showMessage(String message) {
        showSnackbar(getRoot(), message);
    }

    @Override
    public void showMessage(int stringRes) {
        showSnackbar(getRoot(), stringRes);
    }

}
