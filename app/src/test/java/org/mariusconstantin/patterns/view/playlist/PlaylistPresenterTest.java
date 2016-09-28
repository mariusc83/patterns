package org.mariusconstantin.patterns.view.playlist;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mariusconstantin.patterns.BuildConfig;
import org.mariusconstantin.patterns.log.ILogger;
import org.mariusconstantin.patterns.repo.IPlaylistRepository;
import org.mariusconstantin.patterns.repo.model.Playlist;
import org.mariusconstantin.patterns.view.playlist.model.PlaylistViewModel;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by MConstantin on 10/3/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class PlaylistPresenterTest {

    @Mock
    IPlaylistRepository mMockPlaylistRepository;

    @Mock
    ILogger mMockLogger;

    PlaylistPresenter mPlaylistPresenter;

    @Mock
    Action1<PlaylistViewModel> mMockAction;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPlaylistPresenter = new PlaylistPresenter(mMockPlaylistRepository, mMockLogger);
    }

    @Test
    public void test_first_subscription() throws Exception {
        // given
        final ArgumentCaptor<PlaylistViewModel> argumentCaptor = ArgumentCaptor.forClass
                (PlaylistViewModel.class);
        final long playlistId = 1213L;
        final Playlist mockPlaylist = mock(Playlist.class);

        final Observable<Playlist> playlistObservable = Observable.create(new Observable.OnSubscribe<Playlist>() {
            @Override
            public void call(Subscriber<? super Playlist> subscriber) {
                subscriber.onNext(mockPlaylist);
            }
        }).subscribeOn(AndroidSchedulers.mainThread());
        given(mMockPlaylistRepository.getPlaylist(playlistId)).willReturn(playlistObservable);

        // when
        final Subscription subscription = mPlaylistPresenter.onResume(mMockAction, playlistId);

        // then
        assertThat(subscription).isNotNull();
        verify(mMockAction).call(argumentCaptor.capture());
    }
}