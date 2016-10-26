package org.mariusconstantin.patterns.view.playlist;

import android.support.annotation.NonNull;

import org.mariusconstantin.patterns.repo.model.Track;
import org.mariusconstantin.patterns.view.IPresenter;
import org.mariusconstantin.patterns.view.IView;
import org.mariusconstantin.patterns.view.playlist.model.PlaylistViewModel;
import org.mariusconstantin.patterns.view.playlist.model.TrackDetailViewModel;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by MConstantin on 9/28/2016.
 */

public interface IPlaylistContract {

    interface IPlaylistPresenter extends IPresenter {

        Subscription onResume(Action1<PlaylistViewModel> consumer, long playlistId);
    }

    interface ITrackDetailPresenter extends IPresenter {
        Subscription onResume(Action1<TrackDetailViewModel> consumer, @NonNull Track track);
    }

    interface IPlaylistView extends IView {

    }
}
