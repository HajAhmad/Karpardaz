package com.s.karpardaz.base;

public interface NetworkCallback {
    void onConnectionError();
    interface AuthorizationError {
        void onUnauthorized();
    }

}
