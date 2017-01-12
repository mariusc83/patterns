package org.mariusconstantin.patterns.view.playlist.di;

import android.view.LayoutInflater;

import org.mariusconstantin.patterns.log.ILogger;
import org.mariusconstantin.patterns.repo.IPlaylistRepository;
import org.mariusconstantin.patterns.repo.di.RepositoriesSubcomponent;
import org.mariusconstantin.patterns.view.di.FragmentScope;
import org.mariusconstantin.patterns.view.playlist.IPlaylistContract;
import org.mariusconstantin.patterns.view.playlist.adapter.PlaylistAdapter;
import org.mariusconstantin.patterns.view.playlist.interactor.IPlaylistInteractor;
import org.mariusconstantin.patterns.view.playlist.interactor.PlaylistInteractor;
import org.mariusconstantin.patterns.view.playlist.presenter.PlaylistPresenter;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MConstantin on 9/28/2016.
 */
@Module
public class PlaylistModule {

    @Mock
    ILogger mMockLogger;

    @Mock
    IPlaylistInteractor mMockPlaylistInteractor;

    @Mock
    IPlaylistRepository mMockPlaylistRepository;

    public PlaylistModule() {
        MockitoAnnotations.initMocks(this);
    }

    @FragmentScope
    @Provides
    public IPlaylistContract.IPlaylistPresenter providePlaylistPresenter(IPlaylistInteractor mPlaylistInteractor,
                                                                         ILogger logger) {
        return new PlaylistPresenter(mMockPlaylistInteractor, mMockLogger);
    }

    @FragmentScope
    @Provides
    public IPlaylistRepository providePlaylistRepository(RepositoriesSubcomponent.Builder builder) {
        return builder.build().getPlaylistRepository();
    }


    @FragmentScope
    @Provides
    public IPlaylistInteractor providePlaylistInteractor(IPlaylistRepository playlistRepository) {
        return new PlaylistInteractor(mMockPlaylistRepository);
    }

    @FragmentScope
    @Provides
    public PlaylistAdapter providePlayListAdapter(LayoutInflater layoutInflater) {
        return new PlaylistAdapter(layoutInflater);
    }
}
