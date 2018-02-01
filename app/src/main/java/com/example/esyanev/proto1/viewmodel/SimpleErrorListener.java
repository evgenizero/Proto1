package com.example.esyanev.proto1.viewmodel;

/**
 * Created by esyanev on 25/01/18.
 */

public class SimpleErrorListener {

    private OnErrorCallback errorCallback;

    public SimpleErrorListener(OnErrorCallback errorCallback) {
        this.errorCallback = errorCallback;
    }

    public static SimpleErrorListener onError(OnErrorCallback errorCallback) {
        return new SimpleErrorListener(errorCallback);
    }

    public OnErrorCallback getErrorCallback() {
        return errorCallback;
    }
}
