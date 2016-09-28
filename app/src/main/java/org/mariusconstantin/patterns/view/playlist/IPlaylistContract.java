package org.mariusconstantin.patterns.view.playlist;

import org.mariusconstantin.patterns.view.IPresenter;
import org.mariusconstantin.patterns.view.IView;
import org.mariusconstantin.patterns.view.playlist.model.PlaylistViewModel;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by MConstantin on 9/28/2016.
 */

public interface IPlaylistContract {

    interface IPlaylistPresenter extends IPresenter {

        Subscription onResume(Action1<PlaylistViewModel> consumer, long playlistId);
    }

    interface IPlaylistView extends IView {

    }
}
