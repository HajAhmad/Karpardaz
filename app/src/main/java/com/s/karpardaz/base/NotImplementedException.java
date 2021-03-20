package com.s.karpardaz.base;

public class NotImplementedException extends RuntimeException {
    public NotImplementedException() {
        super("This method has not been implemented.");
    }
}
