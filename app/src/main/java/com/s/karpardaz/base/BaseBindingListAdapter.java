package com.s.karpardaz.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseBindingListAdapter extends BaseListAdapter<BaseBindingListAdapter.BindingViewHolder,
        BaseListItem, Void>{

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getLayoutId();
    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, int position) {
        getItem(position).bind(holder.binding);
        holder.binding.executePendingBindings();
    }

    static class BindingViewHolder extends RecyclerView.ViewHolder{
        final ViewDataBinding binding;

        public BindingViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
