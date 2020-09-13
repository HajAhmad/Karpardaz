package com.s.karpardaz.base;

public interface BaseCallback<T> {
    void onSuccess(T result);
    <F extends Throwable> void onFailure(F failure);

}
