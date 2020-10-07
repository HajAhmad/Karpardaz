package com.s.karpardaz.base;

import androidx.databinding.ViewDataBinding;

public class BaseListItem extends BaseModel {
    private final Object data;
    private final int layoutId;


    public BaseListItem(long id, String uuid, Object data, int layoutId) {
        super(id, uuid);
        this.data = data;
        this.layoutId = layoutId;
    }

    public Object getData() {
        return data;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void bind(ViewDataBinding dataBinding) {
        dataBinding.setVariable((int) id, data);
    }
}
