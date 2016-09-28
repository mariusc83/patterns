package org.mariusconstantin.patterns;

import android.app.Application;
import android.support.annotation.NonNull;

import org.mariusconstantin.patterns.di.ApplicationComponent;
import org.mariusconstantin.patterns.di.ApplicationModule;
import org.mariusconstantin.patterns.di.DaggerApplicationComponent;
import org.mariusconstantin.patterns.network.IApiService;
import org.mariusconstantin.patterns.network.NetworkModule;


/**
 * Created by MConstantin on 9/28/2016.
 */

public class PatternsApplication extends Application {
    private static PatternsApplication mInstance;
    private ApplicationComponent mApplicationComponent;

    public PatternsApplication() {
        super();
        mInstance = this;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(IApiService.BASE_API_URL))
                .build();
    }

    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @NonNull
    public static PatternsApplication getInstance() {
        return mInstance;
    }
}
