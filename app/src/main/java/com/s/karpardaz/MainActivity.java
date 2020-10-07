package com.s.karpardaz;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.s.karpardaz.base.BaseActivity;
import com.s.karpardaz.costdialog.CostDetailDialog;
import com.s.karpardaz.home.HomeFragment;


public class MainActivity extends BaseActivity implements
        HomeFragment.OnCostListFragmentInteractionListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment fragment = HomeFragment.newInstance();
        fragment.setInteractionListener(this);
        getSupportFragmentManager().beginTransaction().add(
                fragment, HomeFragment.TAG
        ).commit();

    }

    @Override
    public void openAddCostFragment() {
        CostDetailDialog.newCostDialog().show(getSupportFragmentManager(), CostDetailDialog.TAG);
    }
}
