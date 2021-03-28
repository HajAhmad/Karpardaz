package com.s.karpardaz.cost;

import com.s.karpardaz.base.ui.BaseFinFragment;

public class CostFragment extends BaseFinFragment<CostFragment.OnCostListFragmentInteractionListener> {

    public static final String TAG = CostFragment.class.getSimpleName();

    public static CostFragment newInstance() {
        return new CostFragment();
    }

    public interface OnCostListFragmentInteractionListener {
        void openAddCostDialog();
    }

}
