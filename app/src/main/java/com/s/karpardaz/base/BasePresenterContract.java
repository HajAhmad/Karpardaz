package com.s.karpardaz.base;

public interface BasePresenterContract<V> {

    void takeView(V view);

    void dropView();

    default void start() {
        throw new NotImplementedException();
    }

}
