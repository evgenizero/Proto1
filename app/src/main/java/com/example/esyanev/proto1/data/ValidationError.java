package com.example.esyanev.proto1.data;

/**
 * Created by esyanev on 26/01/18.
 */

public class ValidationError {
    private final ERROR error;

    ValidationError(ERROR error) {
        this.error = error;
    }

    public ERROR getError() {
        return error;
    }

    public enum ERROR {
        WRONG_INPUT

    }
}
