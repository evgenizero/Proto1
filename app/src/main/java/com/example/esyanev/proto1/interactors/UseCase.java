package com.example.esyanev.proto1.interactors;

import com.example.esyanev.proto1.data.Resource;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by esyanev on 23/01/18.
 */

public abstract class UseCase<INPUT, OUTPUT> {

    private CompositeDisposable disposables = new CompositeDisposable();

    public void execute(Input<INPUT> request, DisposableSubscriber<Resource<OUTPUT>> subscriber) {
        getData(request.getInput())
                .subscribeOn(Schedulers.newThread())
                .observeOn(request.getObserverScheduler())
                .subscribe(subscriber);

        disposables.add(subscriber);
    }

    public abstract Flowable<Resource<OUTPUT>> getData(INPUT input);

    public void cancel() {
        disposables.clear();
    }

}
