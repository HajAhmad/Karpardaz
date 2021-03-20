package com.s.karpardaz.base.model;

public class RemoteResponse<T, E extends Throwable> {
    private T object;
    private E throwable;

    public RemoteResponse() {
    }

    public RemoteResponse(T object, E throwable) {
        this.object = object;
        this.throwable = throwable;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public void setThrowable(E throwable) {
        this.throwable = throwable;
    }

    public T getObject() {
        return object;
    }

    public E getThrowable() {
        return throwable;
    }
}
