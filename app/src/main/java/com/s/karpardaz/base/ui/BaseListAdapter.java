package com.s.karpardaz.base.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.s.karpardaz.base.model.BaseViewItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public abstract class BaseListAdapter<VH extends RecyclerView.ViewHolder, T extends BaseViewItem, Listener>
        extends RecyclerView.Adapter<VH> {

    private List<T> mList;
    private Listener mListener;

    protected BaseListAdapter() {
        this(null);
    }

    protected BaseListAdapter(@Nullable Listener listener) {
        mList = new ArrayList<>();
        if (listener != null) mListener = listener;
    }

    @NonNull
    @Override
    public abstract VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @NonNull
    protected View getRootView(ViewGroup parent, @LayoutRes int layoutId) {
        return LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
    }

    @Override
    public abstract void onBindViewHolder(@NonNull VH holder, int position);

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public T getItem(int index) {
        return mList.get(index);
    }

    public void setItems(@NonNull Collection<? extends T> itemList) {
        if (getItemCount() > 0) clear();

        mList = new ArrayList<>(itemList);
        notifyItemRangeInserted(0, mList.size());
    }

    public void addItem(@NonNull T item) {
        addItemAt(item, -1);
    }

    public void addItemAt(@NonNull T item, int index) {
        if (index == -1) {
            mList.add(item);
            notifyItemInserted(getItemCount() - 1);
        } else if (index > -1) {
            mList.add(index, item);
            notifyItemInserted(index);
        }
    }

    public void removeItemAt(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        int currentSize = getItemCount();
        mList = new ArrayList<>();
        notifyItemRangeRemoved(0, currentSize);
    }

    public int getItemPositionById(String id) {
        for (int i = 0; i < mList.size(); i++)
            if (Objects.equals(id, mList.get(i).getId()))
                return i;

        return -1;
    }

    public void setItemAt(int position, T item) {
        mList.set(position, item);
        notifyItemChanged(position);
    }

    @NonNull
    protected Listener getListener() {
        requireNonNull(mListener);
        return mListener;
    }
}
