package org.mariusconstantin.patterns.repo;

import org.mariusconstantin.patterns.repo.model.Playlist;

import rx.Observable;

/**
 * Created by MConstantin on 9/28/2016.
 */

public class LocalPlaylistRepository implements IPlaylistRepository {
    // TODO: 10/3/2016 Persist it based on playlist id,
    private Observable<Playlist> mCachedObservable;

    @Override
    public Observable<Playlist> getPlaylist(long id) {
        return mCachedObservable;
    }

    @Override
    public void cache(Observable<Playlist> playlistObservable) {
        mCachedObservable = playlistObservable;
    }

    @Override
    public void clearCache() {
        mCachedObservable = null;
    }
}
