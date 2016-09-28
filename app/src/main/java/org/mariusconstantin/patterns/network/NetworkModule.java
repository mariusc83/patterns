package org.mariusconstantin.patterns.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import org.mariusconstantin.patterns.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by MConstantin on 9/28/2016.
 */

@Module
public class NetworkModule {
    @NonNull
    private final String mAppBaseUrl;

    public NetworkModule(@NonNull String mAppBaseUrl) {
        this.mAppBaseUrl = mAppBaseUrl;
    }

    @Singleton
    @Provides
    public RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder().setEndpoint(mAppBaseUrl)
                .setConverter(new GsonConverter(new Gson()))
                .setLogLevel((BuildConfig.DEBUG ?
                        RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE))
                .build();
    }

    @Singleton
    @Provides
    public IApiService provideApiService(RestAdapter restAdapter) {
        return restAdapter.create(IApiService.class);
    }
}
