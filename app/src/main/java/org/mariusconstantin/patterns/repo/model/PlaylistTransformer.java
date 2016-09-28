package org.mariusconstantin.patterns.repo.model;

import android.support.annotation.NonNull;

import org.mariusconstantin.patterns.common.function.Function;
import org.mariusconstantin.patterns.network.JSPlaylist;
import org.mariusconstantin.patterns.network.JSTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MConstantin on 9/28/2016.
 */

public class PlaylistTransformer {

    @NonNull
    private final JSPlaylist mJsPlaylist;
    private Function<Track, JSTrack> mTrackMapper;

    private PlaylistTransformer(@NonNull JSPlaylist mJsPlaylist) {
        this.mJsPlaylist = mJsPlaylist;
    }

    public static PlaylistTransformer from(@NonNull JSPlaylist playlist) {
        return new PlaylistTransformer(playlist);
    }

    public PlaylistTransformer mapTrack(Function<Track, JSTrack> trackMapper) {
        mTrackMapper = trackMapper;
        return this;
    }


    public Playlist transform() {
        final List<Track> tracks = new ArrayList<>(mJsPlaylist.data.length);
        if (mTrackMapper != null) {
            for (JSTrack track : mJsPlaylist.data) {
                tracks.add(mTrackMapper.apply(track));
            }
        }

        return Playlist.builder().tracks(tracks).build();
    }

}
