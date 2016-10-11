package org.mariusconstantin.patterns.repo;

import org.mariusconstantin.patterns.repo.model.Playlist;

import rx.Observable;

/**
 * Created by MConstantin on 9/28/2016.
 */

public interface IPlaylistRepository {

    Observable<Playlist> getPlaylist(long id);

    void cache(Observable<Playlist> playlistObservable);

    void clearCache();
}
