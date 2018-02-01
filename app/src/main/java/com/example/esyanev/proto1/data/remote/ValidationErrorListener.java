package com.example.esyanev.proto1.data.remote;

/**
 * Created by esyanev on 25/01/18.
 */

public interface ValidationErrorListener extends ErrorListener {

    void onValidationError(String message);
}
