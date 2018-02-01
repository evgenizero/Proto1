package com.example.esyanev.proto1.viewmodel;

import android.support.annotation.NonNull;

import com.example.esyanev.proto1.data.Resource;
import com.example.esyanev.proto1.errors.ConnectionError;
import com.example.esyanev.proto1.errors.Error;
import com.example.esyanev.proto1.errors.GeneralErrorHandler;
import com.example.esyanev.proto1.errors.TimeoutError;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by esyanev on 29/01/18.
 */

public abstract class UseCaseSubscriber<DATA>
        extends DisposableSubscriber<Resource<DATA>> implements GeneralErrorHandler {

    @Override
    public void onNext(Resource<DATA> resource) {
        switch (resource.status) {
            case ERROR: {
                // this needs to be refactored to create the correct Error
                Error error = (Error) resource.error;

                if (!onAnyError(error)) {
                    final boolean handled;
                    if (resource.error instanceof ConnectionError) {
                        handled = onConnectionError(error);
                    } else if (resource.error instanceof TimeoutError) {
                        handled = onTimeoutError(error);
                    } else {
                        handled = false;
                    }

                    if (!handled) {
                        onGeneralError(error);
                    }
                }
                return;
            }
            case LOADING: {
                onProgress();
                return;
            }
        }
    }

    public void onProgress() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable t) {
        // Handle all the errors we were not expecting here
    }
}
