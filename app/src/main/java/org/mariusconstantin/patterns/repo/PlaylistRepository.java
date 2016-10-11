package org.mariusconstantin.patterns.repo;

import android.support.annotation.NonNull;

import org.mariusconstantin.patterns.repo.di.qualifiers.LocalPlaylistRepo;
import org.mariusconstantin.patterns.repo.di.qualifiers.NetworkPlaylistRepo;
import org.mariusconstantin.patterns.repo.model.Playlist;


import rx.Observable;

/**
 * Created by MConstantin on 9/28/2016.
 */

public class PlaylistRepository implements IPlaylistRepository {

    // TODO: 10/11/2016 Add an Observer pattern here to be notified when to clear the cache
    @NetworkPlaylistRepo
    private final IPlaylistRepository mNetworkRepository;
    @LocalPlaylistRepo
    private final IPlaylistRepository mLocalRepository;


    public PlaylistRepository(@NetworkPlaylistRepo @NonNull IPlaylistRepository mNetworkRepository,
                              @LocalPlaylistRepo @NonNull IPlaylistRepository mLocalRepository) {
        this.mNetworkRepository = mNetworkRepository;
        this.mLocalRepository = mLocalRepository;
    }

    @Override
    public Observable<Playlist> getPlaylist(long id) {
        Observable<Playlist> cachedObservable = mLocalRepository.getPlaylist(id);
        if (cachedObservable == null) {
            cachedObservable = mNetworkRepository.getPlaylist(id).cache();
            cache(cachedObservable);
        }
        return cachedObservable;
    }


    @Override
    public void cache(Observable<Playlist> playlistObservable) {
        mLocalRepository.cache(playlistObservable);
    }

    @Override
    public void clearCache() {
        mLocalRepository.clearCache();
    }
}
