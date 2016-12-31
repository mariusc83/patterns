package org.mariusconstantin.patterns.view.playlist.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.mariusconstantin.patterns.log.ILogger;
import org.mariusconstantin.patterns.repo.model.Track;
import org.mariusconstantin.patterns.view.di.FragmentScope;
import org.mariusconstantin.patterns.view.playlist.IPlaylistContract;
import org.mariusconstantin.patterns.view.playlist.model.TrackDetailViewModel;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by MConstantin on 10/25/2016.
 */

@FragmentScope
public class TrackDetailPresenter implements IPlaylistContract.ITrackDetailPresenter {


    private static final String TAG = TrackDetailPresenter.class.getSimpleName();
    private BehaviorSubject<TrackDetailViewModel> mModelBehaviorSubject;

    @NonNull
    private final ILogger mLogger;

    @Inject
    public TrackDetailPresenter(@NonNull ILogger mLogger) {
        this.mLogger = mLogger;
    }

    @Override
    public Subscription onResume(Action1<TrackDetailViewModel> consumer, @NonNull Track track) {
        if (mModelBehaviorSubject == null) {
            mModelBehaviorSubject = BehaviorSubject.create();
            Observable.just(track)
                    .map(new Func1<Track, TrackDetailViewModel>() {
                        @Override
                        public TrackDetailViewModel call(Track track) {
                            return TrackDetailViewModel
                                    .builder()
                                    .title(track.title())
                                    .artistName(track
                                            .artistName())
                                    .albumCoverUrl(track.albumCoverUrl())
                                    .build();
                        }
                    })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mModelBehaviorSubject);
        }
        return mModelBehaviorSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mLogger.e(TAG, throwable);
                        // reset the subject
                        mModelBehaviorSubject = null;
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
