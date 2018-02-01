package com.example.esyanev.proto1.errors;

/**
 * Created by esyanev on 30/01/18.
 */

public class TimeoutError extends Error {
    public TimeoutError(String message) {
        super(message);
    }
}
