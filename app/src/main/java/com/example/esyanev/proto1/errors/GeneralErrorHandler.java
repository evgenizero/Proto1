package com.example.esyanev.proto1.errors;

public interface GeneralErrorHandler {

    default boolean onAnyError(Error error) {
        return false;
    }

    default boolean onGeneralError(Error error) {
        return false;
    }

    default boolean onConnectionError(Error error) {
        return false;
    }

    default boolean onTimeoutError(Error error) {
        return false;
    }
}