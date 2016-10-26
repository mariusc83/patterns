package org.mariusconstantin.patterns.view.playlist.di;

import android.view.LayoutInflater;

import org.mariusconstantin.patterns.log.ILogger;
import org.mariusconstantin.patterns.repo.di.RepositoriesSubcomponent;
import org.mariusconstantin.patterns.view.di.FragmentScope;
import org.mariusconstantin.patterns.view.playlist.IPlaylistContract;
import org.mariusconstantin.patterns.view.playlist.adapter.PlaylistAdapter;
import org.mariusconstantin.patterns.view.playlist.presenter.PlaylistPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MConstantin on 9/28/2016.
 */
@Module
public class PlaylistModule {


    @FragmentScope
    @Provides
    public IPlaylistContract.IPlaylistPresenter providePlaylistPresenter(RepositoriesSubcomponent
                                                                                 .Builder builder, ILogger logger) {
        return new PlaylistPresenter(builder.build().getPlaylistRepository(), logger);
    }


    @FragmentScope
    @Provides
    public PlaylistAdapter providePlayListAdapter(LayoutInflater layoutInflater) {
        return new PlaylistAdapter(layoutInflater);
    }
}
