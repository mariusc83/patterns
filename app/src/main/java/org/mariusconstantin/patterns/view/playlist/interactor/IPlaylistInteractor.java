package org.mariusconstantin.patterns.view.playlist.interactor;

import org.mariusconstantin.patterns.repo.model.Playlist;

import rx.Observable;

/**
 * Created by Marius on 12/31/2016.
 */

public interface IPlaylistInteractor {

    Observable<Playlist> getPlaylistObservable(long playlistId);
}
