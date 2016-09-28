package org.mariusconstantin.patterns.di;

import android.content.Context;
import android.support.annotation.NonNull;

import org.mariusconstantin.patterns.BuildConfig;
import org.mariusconstantin.patterns.log.DebugLogger;
import org.mariusconstantin.patterns.log.DefaultLogger;
import org.mariusconstantin.patterns.log.ILogger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MConstantin on 9/28/2016.
 */

@Module
public class ApplicationModule {

    @ApplicationContext
    @NonNull
    private final Context mAppContext;

    public ApplicationModule(@NonNull Context mAppContext) {
        this.mAppContext = mAppContext;
    }

    @ApplicationContext
    @Singleton
    @Provides
    public Context provideAppContext() {
        return mAppContext;
    }

    @Singleton
    @Provides
    public ILogger provideLogger() {
        return BuildConfig.DEBUG ? new DebugLogger() : new DefaultLogger();
    }

}
