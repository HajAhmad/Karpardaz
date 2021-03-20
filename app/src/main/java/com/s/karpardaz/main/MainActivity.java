package com.s.karpardaz.main;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.s.karpardaz.R;
import com.s.karpardaz.base.mvvm.ViewModelFactory;
import com.s.karpardaz.base.ui.BaseActivity;
import com.s.karpardaz.base.ui.BaseFinFragment;
import com.s.karpardaz.base.ui.OnFinFragmentInteractionListener;
import com.s.karpardaz.base.ui.ProgressDialog;
import com.s.karpardaz.cost.CostFragment;
import com.s.karpardaz.databinding.ActivityMainBinding;
import com.s.karpardaz.income.IncomeFragment;
import com.s.karpardaz.user.EntryDialogFragment;
import com.s.karpardaz.user.UserViewModel;

import ir.huri.jcal.JalaliCalendar;

import static com.s.karpardaz.base.util.view.SnackbarUtil.showSnackbar;


public class MainActivity extends BaseActivity<ActivityMainBinding> implements
        CostFragment.OnCostListFragmentInteractionListener,
        IncomeFragment.OnIncomeFragmentInteractionListener,
        EntryDialogFragment.OnEntryFragmentInteractionListener {

    private UserViewModel mUserVm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding(ActivityMainBinding.inflate(getLayoutInflater()));
        setContentView(getBinding().getRoot());

        mUserVm = obtainUserViewModel(this);

        observeErrors();

        checkUserInfo();

        //        init();
    }

    private void observeErrors() {
        mUserVm.getErrorMessage().observe(this, this::SnackbarMessage);
    }

    public static UserViewModel obtainUserViewModel(final FragmentActivity activity) {
        return new ViewModelProvider(activity, ViewModelFactory.getInstance()).get(UserViewModel.class);
    }

    private void checkUserInfo() {
        mUserVm.getLoggedInUser().observe(this, user -> {
            if (user == null) showUserEntranceDialog();
            else init();
        });
    }

    private void showUserEntranceDialog() {
        EntryDialogFragment.newInstance().show(getSupportFragmentManager(), EntryDialogFragment.TAG);
    }

    private void init() {
        BottomNavigationView navigationView = findViewById(R.id.main_activity_bottombar);
        navigationView.setSelectedItemId(R.id.main_bottombar_cost_item);

        ((TextView) findViewById(R.id.main_today_date))
                .setText(new JalaliCalendar().getDayOfWeekDayMonthString());

        findViewById(R.id.main_action_profile).setOnClickListener(v -> openProfileFragment());

        navigationView.setOnNavigationItemSelectedListener(item -> {
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
    public <L extends OnFinFragmentInteractionListener, T extends BaseFinFragment<L>> void openAddFinDialog(
            T dialog) {

    }

    private void SnackbarMessage(String value) {
        showSnackbar(getBinding().getRoot(), value);
    }


    //    @Override
    //    public void openAddCostDialog() {
    //        CostDetailDialog.newCostDialog().show(getSupportFragmentManager(), CostDetailDialog.TAG);
    //    }


}
