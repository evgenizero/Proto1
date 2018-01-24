package com.example.esyanev.proto1.interactors;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by esyanev on 23/01/18.
 */

public abstract class UseCase<REQUEST, RESPONSE> {

    public abstract Flowable<RESPONSE> execute(REQUEST request);
}
