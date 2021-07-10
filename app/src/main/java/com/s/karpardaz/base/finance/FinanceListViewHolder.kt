package com.s.karpardaz.base.finance

import com.s.karpardaz.base.BaseViewHolder
import com.s.karpardaz.cost.model.CostViewItem
import com.s.karpardaz.databinding.FragmentHomeListItemBinding

class FinanceListViewHolder(itemBinding: FragmentHomeListItemBinding, listener: FinanceListItemInteractionListener) :
        BaseViewHolder<FragmentHomeListItemBinding, CostViewItem, FinanceListItemInteractionListener>(itemBinding, listener) {

    override fun bind(item: CostViewItem, position: Int) {
        binding.root.setOnClickListener {
            listener.onItemClicked(item, position)
        }
        binding.homeItemSubject.text = item.subject;
        binding.homeItemAmount.text = item.amount
        binding.homeItemOrder.text =  item.order.toString()
    }

}