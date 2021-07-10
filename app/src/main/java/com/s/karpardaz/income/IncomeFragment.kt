package com.s.karpardaz.income

import android.os.Bundle
import com.s.karpardaz.base.finance.BaseFinFragment
import com.s.karpardaz.base.finance.BaseFinanceInteractionListener
import com.s.karpardaz.base.finance.FinanceListItemInteractionListener
import com.s.karpardaz.base.ui.BaseInteractionListener


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class IncomeFragment : BaseFinFragment<IncomeFragment.OnIncomeFragmentInteractionListener>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
    }

    companion object {
        @JvmStatic
        val TAG = IncomeFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                IncomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    interface OnIncomeFragmentInteractionListener : BaseFinanceInteractionListener {

    }
}