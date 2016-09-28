package org.mariusconstantin.patterns.view.playlist.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import org.mariusconstantin.patterns.repo.model.Track;

import java.util.List;

/**
 * Created by MConstantin on 9/28/2016.
 */

@AutoValue
public abstract class PlaylistViewModel implements Parcelable {

    public abstract List<Track> tracks();

    public static Builder builder() {
        return new AutoValue_PlaylistViewModel.Builder();
    }

    @AutoValue.Builder
    public interface Builder {
        Builder tracks(List<Track> tracks);

        PlaylistViewModel build();
    }
}
