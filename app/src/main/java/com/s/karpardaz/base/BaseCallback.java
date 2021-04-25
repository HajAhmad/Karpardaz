package com.s.karpardaz.base;

public interface BaseCallback<T> {

    void onSuccess(T result);

    default void onFailure(Throwable t) {
        throw new NotImplementedException();
    }
}
