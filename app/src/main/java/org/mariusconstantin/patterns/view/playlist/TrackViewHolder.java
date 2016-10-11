package org.mariusconstantin.patterns.view.playlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import org.mariusconstantin.patterns.databinding.TrackRowLayoutBinding;
import org.mariusconstantin.patterns.repo.model.Track;

/**
 * Created by MConstantin on 9/28/2016.
 */

public class TrackViewHolder extends RecyclerView.ViewHolder {

    @NonNull
    private final TrackRowLayoutBinding mBinding;

    public TrackViewHolder(@NonNull TrackRowLayoutBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }


    public void bind(@NonNull Track track) {
        mBinding.setTrack(track);
    }

}
