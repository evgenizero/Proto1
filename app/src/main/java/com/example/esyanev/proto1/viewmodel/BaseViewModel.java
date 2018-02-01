package com.example.esyanev.proto1.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.esyanev.proto1.interactors.UseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by esyanev on 29/01/18.
 */

public class BaseViewModel extends ViewModel {

    private List<UseCase> useCases = new ArrayList<>();

    public BaseViewModel(@NonNull UseCase useCase, UseCase... usecases) {
        this.useCases.add(useCase);
        this.useCases.addAll(Arrays.asList(usecases));
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        for (UseCase useCase : useCases) {
            useCase.cancel();
        }
    }
}
