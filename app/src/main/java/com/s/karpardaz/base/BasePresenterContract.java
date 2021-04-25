package com.s.karpardaz.base;

public interface BasePresenterContract<V> {

    void takeView(V view);

    void dropView();

}
