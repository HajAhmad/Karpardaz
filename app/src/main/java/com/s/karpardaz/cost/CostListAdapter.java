package com.s.karpardaz.cost;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.s.karpardaz.base.BaseViewHolder;
import com.s.karpardaz.base.ui.BaseItemInteractionListener;
import com.s.karpardaz.base.ui.BaseListAdapter;
import com.s.karpardaz.cost.model.CostViewItem;
import com.s.karpardaz.databinding.FragmentHomeListItemBinding;

public class CostListAdapter extends BaseListAdapter<CostListAdapter.ItemViewHolder,
    CostViewItem, BaseItemInteractionListener<CostViewItem>> {

    public CostListAdapter(@Nullable BaseItemInteractionListener<CostViewItem> costViewItemBaseItemInteractionListener) {
        super(costViewItemBaseItemInteractionListener);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(FragmentHomeListItemBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false
        ), getListener());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(getItem(position), position);
    }

    public static class ItemViewHolder extends BaseViewHolder<FragmentHomeListItemBinding, CostViewItem, BaseItemInteractionListener<CostViewItem>> {

        public ItemViewHolder(@NonNull FragmentHomeListItemBinding itemBinding,
            @NonNull BaseItemInteractionListener<CostViewItem> listener) {
            super(itemBinding, listener);
        }

        @Override
        public void bind(@NonNull CostViewItem item, int position) {
            getBinding().getRoot().setOnClickListener(v ->
                getListener().onItemClicked(item, position));
            getBinding().homeItemSubject.setText(item.getSubject());
            getBinding().homeItemAmount.setText(item.getAmount());
            getBinding().homeItemOrder.setText(String.valueOf(item.getOrder()));
        }
    }

}
