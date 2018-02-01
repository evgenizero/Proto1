/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.esyanev.proto1.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.esyanev.proto1.errors.Error;

import static com.example.esyanev.proto1.data.Status.ERROR;
import static com.example.esyanev.proto1.data.Status.LOADING;
import static com.example.esyanev.proto1.data.Status.SUCCESS;

public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final Throwable error;

    public Resource(@NonNull Status status, @Nullable T data) {
        this.status = status;
        this.data = data;
        this.error = null;
    }

    private Resource(@NonNull Status status, @Nullable Throwable error) {
        this.status = status;
        this.data = null;
        this.error = error;
    }

    public Resource(@NonNull Status status) {
        this.status = status;
        this.data = null;
        this.error = null;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data);
    }

    public static <T> Resource<T> error(Throwable throwable) {
        return new Resource<>(ERROR, throwable);
    }

    public static Resource loading() {
        return new Resource(LOADING);
    }
}
