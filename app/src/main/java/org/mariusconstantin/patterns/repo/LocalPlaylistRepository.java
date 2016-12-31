package org.mariusconstantin.patterns.repo;

import android.util.ArrayMap;

import org.mariusconstantin.patterns.repo.model.Playlist;

import rx.Observable;

/**
 * Created by MConstantin on 9/28/2016.
 */

public class LocalPlaylistRepository implements IPlaylistRepository {
    // TODO: 10/3/2016 Use/Build a primitive based ArrayLongMap
    private final ArrayMap<Long, Observable<Playlist>> mCacheMap = new ArrayMap<>(10);

    @Override
    public Observable<Playlist> getPlaylist(long id) {
        return mCacheMap.get(id);
    }

    @Override
    public void cache(long playlistId, Observable<Playlist> playlistObservable) {
        mCacheMap.put(playlistId, playlistObservable);
    }

    @Override
    public void clearCache() {
        mCacheMap.clear();
    }
}
