package org.mariusconstantin.patterns.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mariusconstantin.patterns.BuildConfig;
import org.mariusconstantin.patterns.repo.model.Playlist;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import rx.Observable;
import rx.Subscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;
import static org.mockito.Matchers.anyLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by MConstantin on 9/28/2016.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class PlaylistRepositoryTest {


    @Test
    public void when_get_playlist_first_called_should_interrogate_the_NetworkRepository() throws Exception {
        // given
        final Playlist mockPlaylist = mock(Playlist.class);

        final Observable<Playlist> playlistObservable = Observable.create(new Observable.OnSubscribe<Playlist>() {
            @Override
            public void call(Subscriber<? super Playlist> subscriber) {
                subscriber.onNext(mockPlaylist);
            }
        });
        final IPlaylistRepository mockNetworkRepo = mock(IPlaylistRepository.class);
        final IPlaylistRepository mockLocalRepo = mock(IPlaylistRepository.class);
        final PlaylistRepository playlistRepository = new PlaylistRepository(mockNetworkRepo,
                mockLocalRepo);
        final long playlistId = 11L;
        given(mockNetworkRepo.getPlaylist(anyLong())).willReturn(playlistObservable);

        // when
        final Observable<Playlist> returnedObservable = playlistRepository.getPlaylist(playlistId);

        // then
        assertThat(returnedObservable).isNotNull();
        final Playlist returnedPlaylist = returnedObservable.toBlocking().first();
        assertThat(returnedPlaylist).isEqualTo(mockPlaylist);
        verify(mockNetworkRepo).getPlaylist(eq(playlistId));
    }

    @Test
    public void when_get_playlist_second_called_should_not_interrogate_the_NetworkRepository()
            throws Exception {
        // given
        final Playlist mockPlaylist = mock(Playlist.class);

        final Observable<Playlist> playlistObservable = Observable.create(new Observable.OnSubscribe<Playlist>() {
            @Override
            public void call(Subscriber<? super Playlist> subscriber) {
                subscriber.onNext(mockPlaylist);
            }
        });
        final IPlaylistRepository mockNetworkRepo = mock(IPlaylistRepository.class);
        final PlaylistRepository playlistRepository = new PlaylistRepository(mockNetworkRepo,
                new LocalPlaylistRepository());
        final long playlistId = 11L;
        given(mockNetworkRepo.getPlaylist(anyLong())).willReturn(playlistObservable);
        playlistRepository.getPlaylist(playlistId);
        reset(mockNetworkRepo);
        // when
        final Observable<Playlist> returnedObservable = playlistRepository.getPlaylist(playlistId);

        // then
        assertThat(returnedObservable).isNotNull();
        final Playlist returnedPlaylist = returnedObservable.toBlocking().first();
        assertThat(returnedPlaylist).isEqualTo(mockPlaylist);
        verifyZeroInteractions(mockNetworkRepo);
    }

    @Test
    public void when_get_playlist_called_multiple_times_should_not_interrogate_the_NetworkRepository()
            throws Exception {
        // given
        final Playlist mockPlaylist = mock(Playlist.class);

        final Observable<Playlist> playlistObservable = Observable.create(new Observable.OnSubscribe<Playlist>() {
            @Override
            public void call(Subscriber<? super Playlist> subscriber) {
                subscriber.onNext(mockPlaylist);
            }
        });
        final IPlaylistRepository mockNetworkRepo = mock(IPlaylistRepository.class);
        final PlaylistRepository playlistRepository = new PlaylistRepository(mockNetworkRepo,
                new LocalPlaylistRepository());
        final long playlistId = 11L;
        given(mockNetworkRepo.getPlaylist(anyLong())).willReturn(playlistObservable);
        playlistRepository.getPlaylist(playlistId);
        playlistRepository.getPlaylist(playlistId);
        playlistRepository.getPlaylist(playlistId);
        playlistRepository.getPlaylist(playlistId);
        reset(mockNetworkRepo);
        // when
        final Observable<Playlist> returnedObservable = playlistRepository.getPlaylist(playlistId);

        // then
        assertThat(returnedObservable).isNotNull();
        final Playlist returnedPlaylist = returnedObservable.toBlocking().first();
        assertThat(returnedPlaylist).isEqualTo(mockPlaylist);
        verifyZeroInteractions(mockNetworkRepo);
    }

}