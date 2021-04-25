package com.s.karpardaz.income;

import com.s.karpardaz.base.ui.BaseFinFragment;
import com.s.karpardaz.base.ui.BaseInteractionListener;

public class IncomeFragment extends BaseFinFragment<IncomeFragment.OnIncomeFragmentInteractionListener> {
    public static final String TAG = IncomeFragment.class.getSimpleName();

    public static IncomeFragment newInstance() {
        return new IncomeFragment();
    }


    public interface OnIncomeFragmentInteractionListener extends BaseInteractionListener {
        void openAddIncomeDialog();
    }

}
