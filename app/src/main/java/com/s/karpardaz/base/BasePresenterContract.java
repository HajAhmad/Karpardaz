package com.s.karpardaz.base;

public interface BasePresenterContract<V> {

    default void start(){
        throw new NotImplementedException();
    }

    void takeView(V view);

    void dropView();

}
