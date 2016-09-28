package org.mariusconstantin.patterns.view.playlist.di;

import org.mariusconstantin.patterns.log.ILogger;
import org.mariusconstantin.patterns.repo.IPlaylistRepository;
import org.mariusconstantin.patterns.view.di.FragmentScope;
import org.mariusconstantin.patterns.view.playlist.IPlaylistContract;
import org.mariusconstantin.patterns.view.playlist.PlaylistPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MConstantin on 9/28/2016.
 */
@Module
public class PlaylistModule {


    @FragmentScope
    @Provides
    public IPlaylistContract.IPlaylistPresenter providePlaylistPresenter(IPlaylistRepository playlistRepository, ILogger logger) {
        return new PlaylistPresenter(playlistRepository, logger);
    }
}
