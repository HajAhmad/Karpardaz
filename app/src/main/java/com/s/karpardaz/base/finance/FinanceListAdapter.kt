package com.s.karpardaz.base.finance

import android.view.LayoutInflater
import android.view.ViewGroup
import com.s.karpardaz.base.ui.BaseListAdapter
import com.s.karpardaz.cost.model.CostViewItem
import com.s.karpardaz.databinding.FragmentHomeListItemBinding

class FinanceListAdapter(listener: FinanceListItemInteractionListener)
    : BaseListAdapter<FinanceListViewHolder, CostViewItem, FinanceListItemInteractionListener>(listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinanceListViewHolder {
        return FinanceListViewHolder(FragmentHomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }

    override fun onBindViewHolder(holder: FinanceListViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

}