package org.mariusconstantin.patterns.view.playlist;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.mariusconstantin.patterns.R;
import org.mariusconstantin.patterns.databinding.TrackRowLayoutBinding;
import org.mariusconstantin.patterns.repo.model.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MConstantin on 9/28/2016.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<TrackViewHolder> {
    @NonNull
    private final LayoutInflater mLayoutInflater;
    private List<Track> mData;

    public PlaylistAdapter(@NonNull LayoutInflater mLayoutInflater) {
        this.mLayoutInflater = mLayoutInflater;
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final TrackRowLayoutBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout
                .track_row_layout, parent, false);
        return new TrackViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }


    public void setData(@NonNull List<Track> data) {
        if (mData == null)
            mData = new ArrayList<>(data.size());

        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
