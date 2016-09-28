package org.mariusconstantin.patterns.di;

import android.content.Context;

import org.mariusconstantin.patterns.log.ILogger;
import org.mariusconstantin.patterns.network.IApiService;
import org.mariusconstantin.patterns.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by MConstantin on 9/28/2016.
 */
@Component(modules = {ApplicationModule.class, NetworkModule.class})
@Singleton
public interface ApplicationComponent {

    @ApplicationContext
    Context provideAppContext();

    IApiService getApiService();

    ILogger getLogger();
}
