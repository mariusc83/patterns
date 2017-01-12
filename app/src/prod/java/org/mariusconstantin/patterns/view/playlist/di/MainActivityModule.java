package org.mariusconstantin.patterns.view.playlist.di;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import org.mariusconstantin.patterns.repo.di.RepositoriesSubcomponent;
import org.mariusconstantin.patterns.view.di.ActivityScope;
import org.mariusconstantin.patterns.view.playlist.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MConstantin on 9/28/2016.
 */
@Module(subcomponents = RepositoriesSubcomponent.class)
public class MainActivityModule {

    @NonNull
    private final MainActivity mMainActivity;

    public MainActivityModule(@NonNull MainActivity mMainActivity) {
        this.mMainActivity = mMainActivity;
    }

    @ActivityScope
    @Provides
    public MainActivity provideMainActivity() {
        return mMainActivity;
    }

    @Provides
    LayoutInflater provideLayoutInflater(MainActivity activity) {
        return activity.getLayoutInflater();
    }
}
