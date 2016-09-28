package org.mariusconstantin.patterns.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by MConstantin on 7/5/2016.
 */
public interface ILogger {
    void d(@NonNull String tag, @NonNull String message, @Nullable Object... params);

    void d(@NonNull String tag,
           @NonNull Throwable t, @NonNull String message, @Nullable Object... params);

    void w(@NonNull String tag, @NonNull String message, @Nullable Object... params);

    void w(@NonNull String tag,
           @NonNull Throwable t, @NonNull String message, @Nullable Object... params);

    void e(@NonNull String tag, @NonNull String message, @Nullable Object... params);

    void e(@NonNull String tag, @NonNull Throwable throwable);

    void e(@NonNull String tag,
           @NonNull Throwable t, @NonNull String message, @Nullable Object... params);


}
