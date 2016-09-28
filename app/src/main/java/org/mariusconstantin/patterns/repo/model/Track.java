package org.mariusconstantin.patterns.repo.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;


/**
 * Created by MConstantin on 9/28/2016.
 */

@AutoValue
public abstract class Track implements Parcelable {
    public abstract long id();

    public abstract String title();

    public abstract String link();


    public static Track create(long id, @NonNull String title, @NonNull String link) {
        return builder().id(id).title(title).link(link).build();
    }

    public static Builder builder() {
        return new AutoValue_Track.Builder();
    }

    @AutoValue.Builder
    public interface Builder {
        Builder id(long x);

        Builder link(String link);

        Builder title(String title);

        Track build();
    }
}
