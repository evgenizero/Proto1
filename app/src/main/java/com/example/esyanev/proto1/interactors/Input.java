package com.example.esyanev.proto1.interactors;

import io.reactivex.Scheduler;

/**
 * Created by esyanev on 29/01/18.
 */

public class Input<INPUT> {

    private INPUT input;
    private Scheduler observerScheduler;

    public Input(INPUT input, Scheduler observerScheduler) {
        this.input = input;
        this.observerScheduler = observerScheduler;
    }

    public INPUT getInput() {
        return input;
    }

    public Scheduler getObserverScheduler() {
        return observerScheduler;
    }
}
