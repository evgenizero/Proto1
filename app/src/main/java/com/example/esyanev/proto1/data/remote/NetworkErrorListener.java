package com.example.esyanev.proto1.data.remote;

/**
 * Created by esyanev on 25/01/18.
 */

public interface NetworkErrorListener extends ErrorListener {

    void onNetworkError(String message);
}
