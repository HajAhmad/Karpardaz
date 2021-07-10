package com.s.karpardaz.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.s.karpardaz.base.model.BaseViewItem;
import com.s.karpardaz.base.ui.BaseItemInteractionListener;

import java.util.Objects;

public abstract class BaseViewHolder<V extends ViewBinding, T extends BaseViewItem,
    L extends BaseItemInteractionListener<T>> extends RecyclerView.ViewHolder {

    private final V mBinding;
    private final L mListener;

    public BaseViewHolder(@NonNull V itemBinding, @NonNull L listener) {
        super(itemBinding.getRoot());
        mBinding = Objects.requireNonNull(itemBinding);
        mListener = Objects.requireNonNull(listener);
    }

    public abstract void bind(@NonNull T item, int position);

    public V getBinding() {
        return mBinding;
    }

    public L getListener() {
        return mListener;
    }

}
