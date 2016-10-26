package org.mariusconstantin.patterns.view.playlist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.mariusconstantin.patterns.databinding.TrackRowLayoutBinding;
import org.mariusconstantin.patterns.repo.model.Track;

/**
 * Created by MConstantin on 9/28/2016.
 */

public class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @NonNull
    private final TrackRowLayoutBinding mBinding;

    private OnItemClickedListener mListener;

    public TrackViewHolder(@NonNull TrackRowLayoutBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
        this.mBinding.getRoot().setOnClickListener(this);
    }


    public void setListener(OnItemClickedListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) mListener.onItemClicked(mBinding.getTrack());
    }

    public void bind(@NonNull Track track) {
        mBinding.setTrack(track);
    }


    public interface OnItemClickedListener {
        void onItemClicked(@NonNull Track track);
    }
}
