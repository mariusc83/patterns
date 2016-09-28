package org.mariusconstantin.patterns.common.function;

import android.support.annotation.NonNull;


/**
 * Created by MConstantin on 9/8/2016.
 */
public interface Function<T, R> {

    @NonNull
    T apply(R reference);
}
