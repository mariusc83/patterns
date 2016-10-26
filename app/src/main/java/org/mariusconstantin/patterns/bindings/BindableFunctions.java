package org.mariusconstantin.patterns.bindings;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.mariusconstantin.patterns.repo.model.Track;

/**
 * Created by MConstantin on 10/25/2016.
 */

public class BindableFunctions {

    @BindingAdapter("bindImage")
    public static void bingImage(@NonNull ImageView imageView, @NonNull Track track) {
            Glide.with(imageView.getContext())
                .load(Uri.parse(track.albumCoverUrl()))
                .asBitmap()
                .centerCrop()
                .into(imageView);
    }
}
