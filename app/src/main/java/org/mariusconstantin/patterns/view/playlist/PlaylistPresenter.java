package org.mariusconstantin.patterns.view.playlist;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.mariusconstantin.patterns.log.ILogger;
import org.mariusconstantin.patterns.repo.IPlaylistRepository;
import org.mariusconstantin.patterns.repo.model.Playlist;
import org.mariusconstantin.patterns.view.playlist.model.PlaylistViewModel;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by MConstantin on 9/28/2016.
 */

public class PlaylistPresenter implements IPlaylistContract.IPlaylistPresenter {

    private static final String TAG = PlaylistPresenter.class.getSimpleName();

    @NonNull
    private final IPlaylistRepository mPlaylistRepository;

    @NonNull
    private final ILogger mLogger;

    private PlaylistViewModel mPlaylistViewModel;

    private final BehaviorSubject<PlaylistViewModel> mViewModelBehaviorSubject = BehaviorSubject
            .create();

    public PlaylistPresenter(@NonNull IPlaylistRepository mPlaylistRepository, @NonNull ILogger mLogger) {
        this.mPlaylistRepository = mPlaylistRepository;
        this.mLogger = mLogger;
    }

    @Override
    public Subscription onResume(Action1<PlaylistViewModel> consumer, long playlistId) {
        if (mPlaylistViewModel != null) {
            mViewModelBehaviorSubject.onNext(mPlaylistViewModel);
        } else {
            refreshData(playlistId);
        }

        return mViewModelBehaviorSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    private void refreshData(long playlistId) {
        final Observable<Playlist> playlistObservable = mPlaylistRepository.getPlaylist(playlistId);
        playlistObservable.subscribe(new Action1<Playlist>() {
            @Override
            public void call(Playlist playlist) {
                mPlaylistViewModel = PlaylistViewModel.builder()
                        .tracks(playlist.tracks())
                        .build();
                mViewModelBehaviorSubject.onNext(mPlaylistViewModel);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mLogger.e(TAG, throwable);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onPause(Bundle savedInstanceState) {

    }
}
