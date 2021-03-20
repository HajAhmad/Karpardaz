package com.s.karpardaz.base.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s.karpardaz.databinding.FragmentHomeListItemBinding;
import com.s.karpardaz.cost.model.CostViewItem;
import com.s.karpardaz.base.model.BaseFinViewEntity;

public class FinListAdapter extends BaseListAdapter<FinListAdapter.ItemViewHolder,
        BaseFinViewEntity, BaseItemInteractionListener<CostViewItem>> {

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(FragmentHomeListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        BaseFinViewEntity item = getItem(position);
        holder.itemBinding.homeItemOrder.setText(item.getOrder());
        holder.itemBinding.homeItemSubject.setText(item.getSubject());
        holder.itemBinding.homeItemAmount.setText(item.getAmount());
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        FragmentHomeListItemBinding itemBinding;

        public ItemViewHolder(@NonNull FragmentHomeListItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;
        }
    }
}
