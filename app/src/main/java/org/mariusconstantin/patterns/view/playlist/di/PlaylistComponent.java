package org.mariusconstantin.patterns.view.playlist.di;

import org.mariusconstantin.patterns.log.ILogger;
import org.mariusconstantin.patterns.repo.di.RepositoriesComponent;
import org.mariusconstantin.patterns.view.di.FragmentScope;
import org.mariusconstantin.patterns.view.playlist.IPlaylistContract;
import org.mariusconstantin.patterns.view.playlist.PlaylistFragment;

import dagger.Component;

/**
 * Created by MConstantin on 9/28/2016.
 */

@Component(dependencies = MainActivityComponent.class, modules = PlaylistModule.class)
@FragmentScope
public interface PlaylistComponent {

    IPlaylistContract.IPlaylistPresenter getPlaylistPresenter();

    void inject(PlaylistFragment playlistFragment);
}
