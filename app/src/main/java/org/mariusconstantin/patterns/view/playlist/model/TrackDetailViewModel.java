package org.mariusconstantin.patterns.view.playlist.model;

import com.google.auto.value.AutoValue;

/**
 * Created by MConstantin on 10/25/2016.
 */

@AutoValue
public abstract class TrackDetailViewModel {

    public abstract String title();

    public abstract String artistName();

    public abstract String albumCoverUrl();

    public static Builder builder() {
        return new AutoValue_TrackDetailViewModel.Builder();
    }

    @AutoValue.Builder
    public interface Builder {
        Builder title(String titleValue);

        Builder artistName(String artistNameValue);

        Builder albumCoverUrl(String albumCoverUrl);

        TrackDetailViewModel build();
    }
}