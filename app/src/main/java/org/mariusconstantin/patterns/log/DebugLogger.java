package org.mariusconstantin.patterns.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Locale;

/**
 * Created by MConstantin on 7/5/2016.
 */
public class DebugLogger implements ILogger {
    @Override
    public void d(@NonNull String tag, @NonNull String message, @Nullable Object... params) {
        Log.d(tag, String.format(Locale.ENGLISH, message, params));
    }

    @Override
    public void d(@NonNull String tag,
                  @NonNull Throwable t, @NonNull String message, @Nullable Object... params) {
        Log.d(tag, String.format(Locale.ENGLISH, message, params), t);
    }

    @Override
    public void w(@NonNull String tag, @NonNull String message, @Nullable Object... params) {
        Log.w(tag, String.format(Locale.ENGLISH, message, params));

    }

    @Override
    public void w(@NonNull String tag,
                  @NonNull Throwable t, @NonNull String message, @Nullable Object... params) {
        Log.w(tag, String.format(Locale.ENGLISH, message, params), t);
    }

    @Override
    public void e(@NonNull String tag, @NonNull String message, @Nullable Object... params) {
        Log.e(tag, String.format(Locale.ENGLISH, message, params));
    }

    @Override
    public void e(@NonNull String tag,
                  @NonNull Throwable t, @NonNull String message, @Nullable Object... params) {
        Log.e(tag, String.format(Locale.ENGLISH, message, params), t);
    }

    @Override
    public void e(@NonNull String tag, @NonNull Throwable throwable) {
        Log.e(tag, throwable.getMessage());
    }
}
