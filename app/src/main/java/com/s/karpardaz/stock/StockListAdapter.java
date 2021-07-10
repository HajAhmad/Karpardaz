package com.s.karpardaz.stock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.s.karpardaz.base.BaseViewHolder;
import com.s.karpardaz.base.ui.BaseItemInteractionListener;
import com.s.karpardaz.base.ui.BaseListAdapter;
import com.s.karpardaz.databinding.LayoutStockAddButtonBinding;
import com.s.karpardaz.databinding.LayoutStockItemBinding;
import com.s.karpardaz.stock.model.StockListItem;

public class StockListAdapter extends BaseListAdapter<RecyclerView.ViewHolder, StockListItem, StockListAdapter.OnItemListener> {

    public static final short TYPE_ADD_BUTTON = -1;
    public static final short TYPE_NORMAL = 0x0001;
    private final OnAddButtonListener mAddButtonListener;

    public StockListAdapter(@Nullable OnItemListener onItemListener, OnAddButtonListener onAddButtonListener) {
        super(onItemListener);
        mAddButtonListener = onAddButtonListener;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType() == TYPE_ADD_BUTTON ? TYPE_ADD_BUTTON : position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ADD_BUTTON) {
            return new AddButtonHolder(LayoutStockAddButtonBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
            ), mAddButtonListener);
        } else {
            return new ViewHolder(LayoutStockItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
            ), getListener());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItem(position).getType() == TYPE_ADD_BUTTON) ((AddButtonHolder) holder).bind();
        else ((ViewHolder) holder).bind(getItem(position), position);
    }

    public static class AddButtonHolder extends RecyclerView.ViewHolder {
        private final LayoutStockAddButtonBinding binding;
        private final OnAddButtonListener mListener;

        public AddButtonHolder(@NonNull LayoutStockAddButtonBinding itemBinding, OnAddButtonListener listener) {
            super(itemBinding.getRoot());
            binding = itemBinding;
            mListener = listener;
        }

        public void bind() {
            binding.layoutStockAddButtonRoot.setOnClickListener(v -> mListener.onAddClicked());
        }
    }

    public static class ViewHolder extends BaseViewHolder<LayoutStockItemBinding, StockListItem, OnItemListener> {

        public ViewHolder(@NonNull LayoutStockItemBinding itemBinding, @NonNull OnItemListener listener) {
            super(itemBinding, listener);
        }

        @Override
        public void bind(@NonNull StockListItem item, int position) {
            getBinding().layoutStockDefault.setVisibility(item.isDefault() ? View.VISIBLE : View.GONE);
            getBinding().layoutStockItemCurrency.setText(item.getCurrency());
            getBinding().layoutStockItemTitle.setText(item.getName());
            getBinding().layoutStockItemOrder.setText(String.valueOf(item.getOrder()));

            getBinding().getRoot().setOnClickListener(v -> getListener().onItemClicked(item, position));
        }
    }

    public interface OnAddButtonListener {
        void onAddClicked();
    }

    public interface OnItemListener extends BaseItemInteractionListener<StockListItem> {

    }
}
