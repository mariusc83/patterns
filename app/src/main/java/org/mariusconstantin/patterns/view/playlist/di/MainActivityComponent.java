package org.mariusconstantin.patterns.view.playlist.di;

import android.view.LayoutInflater;

import org.mariusconstantin.patterns.di.ApplicationComponent;
import org.mariusconstantin.patterns.log.ILogger;
import org.mariusconstantin.patterns.repo.di.RepositoriesSubcomponent;
import org.mariusconstantin.patterns.view.di.ActivityScope;
import org.mariusconstantin.patterns.view.playlist.MainActivity;
import org.mariusconstantin.patterns.view.playlist.flowcontroller.FlowController;
import org.mariusconstantin.patterns.view.playlist.fragment.PlaylistFragment;

import dagger.Component;

/**
 * Created by MConstantin on 9/28/2016.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

    LayoutInflater getLayoutInflater();

    ILogger getLogger();

    RepositoriesSubcomponent.Builder getRepositoriesBuilder();

    FlowController getFlowController();

    void inject(MainActivity activity);


}
