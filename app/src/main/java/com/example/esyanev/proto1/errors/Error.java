package com.example.esyanev.proto1.errors;

/**
 * Created by esyanev on 30/01/18.
 */

public abstract class Error extends Exception{

    private String message;

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
