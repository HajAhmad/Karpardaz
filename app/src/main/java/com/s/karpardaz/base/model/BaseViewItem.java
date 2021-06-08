package com.s.karpardaz.base.model;


public abstract class BaseViewItem {
    private String id;

    public BaseViewItem() {
    }

    public BaseViewItem(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
