package com.s.karpardaz.cost;

import com.s.karpardaz.base.ui.BaseFinFragment;
import com.s.karpardaz.base.ui.OnFinFragmentInteractionListener;

public class CostFragment extends BaseFinFragment<CostFragment.OnCostListFragmentInteractionListener> {

    public static final String TAG = CostFragment.class.getSimpleName();

    public static CostFragment newInstance() {
        return new CostFragment();
    }

    public interface OnCostListFragmentInteractionListener extends OnFinFragmentInteractionListener {
        
    }

}
