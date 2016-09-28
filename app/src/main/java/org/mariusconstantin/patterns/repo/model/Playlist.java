package org.mariusconstantin.patterns.repo.model;


import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * Created by MConstantin on 9/28/2016.
 */


@AutoValue
public abstract class Playlist implements Parcelable {

    public abstract List<Track> tracks();

    public static Playlist.Builder builder() {
        return new AutoValue_Playlist.Builder();
    }

    @AutoValue.Builder
    public interface Builder {
        Builder tracks(List<Track> tracks);

        Playlist build();
    }
}
