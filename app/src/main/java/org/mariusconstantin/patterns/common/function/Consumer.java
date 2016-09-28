package org.mariusconstantin.patterns.common.function;

import android.support.annotation.NonNull;


/**
 * Created by MConstantin on 9/8/2016.
 */
public interface Consumer<T> {

    void accept(@NonNull T model);
}
