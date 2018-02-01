package com.example.esyanev.proto1.errors;

/**
 * Created by esyanev on 30/01/18.
 */

public class ConnectionError extends Error {
    public ConnectionError(String message) {
        super("Connection error: " + message);
    }
}
