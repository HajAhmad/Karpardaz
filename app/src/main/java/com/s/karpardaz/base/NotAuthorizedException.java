package com.s.karpardaz.base;

public class NotAuthorizedException extends Exception {
    public NotAuthorizedException() {
        super("User not authorized.");
    }
}
