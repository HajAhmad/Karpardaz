package com.s.karpardaz.base;

public interface BaseCallback<T> {

    default void onSuccess() {
        onSuccess(null);
    }

    void onSuccess(T result);

    default void onFailure(Throwable t) {
        throw new NotImplementedException();
    }
}
