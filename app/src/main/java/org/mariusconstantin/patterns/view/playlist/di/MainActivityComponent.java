package org.mariusconstantin.patterns.view.playlist.di;

import android.view.LayoutInflater;

import org.mariusconstantin.patterns.di.ApplicationComponent;
import org.mariusconstantin.patterns.log.ILogger;
import org.mariusconstantin.patterns.repo.IPlaylistRepository;
import org.mariusconstantin.patterns.repo.di.RepositoriesComponent;
import org.mariusconstantin.patterns.view.di.ActivityScope;

import dagger.Component;

/**
 * Created by MConstantin on 9/28/2016.
 */

@ActivityScope
@Component(dependencies = RepositoriesComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

    IPlaylistRepository getPlaylistRepository();

    LayoutInflater getLayoutInflater();

    ILogger getLogger();
}
