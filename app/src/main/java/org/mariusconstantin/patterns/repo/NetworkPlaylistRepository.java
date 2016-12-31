package org.mariusconstantin.patterns.repo;

import android.support.annotation.NonNull;

import org.mariusconstantin.patterns.common.function.Function;
import org.mariusconstantin.patterns.network.IApiService;
import org.mariusconstantin.patterns.network.JSPlaylist;
import org.mariusconstantin.patterns.network.JSTrack;
import org.mariusconstantin.patterns.repo.model.Playlist;
import org.mariusconstantin.patterns.repo.model.PlaylistTransformer;
import org.mariusconstantin.patterns.repo.model.Track;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by MConstantin on 9/28/2016.
 */

public class NetworkPlaylistRepository implements IPlaylistRepository {
    @NonNull
    private final IApiService mApiService;


    public NetworkPlaylistRepository(@NonNull IApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<Playlist> getPlaylist(long id) {
        return mApiService
                .getTracks(id)
                .map(new Func1<JSPlaylist, Playlist>() {
                    @Override
                    public Playlist call(JSPlaylist jsPlaylist) {
                        return PlaylistTransformer.from(jsPlaylist).mapTrack(new Function<Track, JSTrack>() {
                            @NonNull
                            @Override
                            public Track apply(JSTrack reference) {
                                return Track.builder()
                                        .id(reference.id)
                                        .link(reference.link)
                                        .title(reference.title)
                                        .albumCoverUrl(reference.album.cover_medium)
                                        .artistName(reference.artist.name)
                                        .build();
                            }
                        }).transform();
                    }
                });
    }

    @Override
    public void cache(long playlistId, Observable<Playlist> playlistObservable) {
        throw new UnsupportedOperationException("Use the LocalPlaylistRepository");

    }

    @Override
    public void clearCache() {
        throw new UnsupportedOperationException("Use the LocalPlaylistRepository");
    }
}
