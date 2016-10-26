package org.mariusconstantin.patterns.view.playlist.bindings;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.mariusconstantin.patterns.repo.model.Track;
import org.mariusconstantin.patterns.view.playlist.model.TrackDetailViewModel;

/**
 * Created by MConstantin on 10/25/2016.
 */

public class BindableFunctions {

    @BindingAdapter("bindImage")
    public static void bingImage(@NonNull ImageView imageView, @Nullable TrackDetailViewModel
            trackDetailViewModel) {
        if (trackDetailViewModel != null) {
            Glide.with(imageView.getContext())
                    .load(Uri.parse(trackDetailViewModel.albumCoverUrl()))
                    .asBitmap()
                    .centerCrop()
                    .into(imageView);
        } else {
            // TODO: 10/26/2016 Load the place holder
        }
    }
}
