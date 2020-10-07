package com.s.karpardaz.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public final class BindingAdapter {
    private BindingAdapter() {
    }

    @androidx.databinding.BindingAdapter("items")
    public static void setRecyclerViewItemList(@NonNull RecyclerView recyclerView,
            @NonNull List<BaseListItem> itemList) {
        BaseBindingListAdapter adapter = (BaseBindingListAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new BaseBindingListAdapter();
            recyclerView.setAdapter(adapter);
        }
        adapter.setItems(itemList);
    }
}
