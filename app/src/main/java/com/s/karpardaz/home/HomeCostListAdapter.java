package com.s.karpardaz.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s.karpardaz.base.BaseItemInteractionListener;
import com.s.karpardaz.base.BaseListAdapter;
import com.s.karpardaz.databinding.FragmentHomeListItemBinding;

public class HomeCostListAdapter extends BaseListAdapter<HomeCostListAdapter.ItemViewHolder,
        CostHomeItem, BaseItemInteractionListener<CostHomeItem>> {

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(FragmentHomeListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        CostHomeItem item = getItem(position);

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private FragmentHomeListItemBinding itemBinding;
        public ItemViewHolder(@NonNull FragmentHomeListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
