package org.mariusconstantin.patterns.view.playlist.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.mariusconstantin.patterns.R;
import org.mariusconstantin.patterns.databinding.TrackRowLayoutBinding;
import org.mariusconstantin.patterns.repo.model.Track;
import org.mariusconstantin.patterns.view.playlist.MainActivity;
import org.mariusconstantin.patterns.view.playlist.flowcontroller.FlowController;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MConstantin on 9/28/2016.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<TrackViewHolder> {
    @NonNull
    private final LayoutInflater mLayoutInflater;
    private List<Track> mData;

    private OnNavigateToTrackDetailsListener mTrackDetailsListener;

    public PlaylistAdapter(@NonNull LayoutInflater mLayoutInflater) {
        this.mLayoutInflater = mLayoutInflater;
    }

    public void setTrackDetailsListener(OnNavigateToTrackDetailsListener trackDetailsListener) {
        this.mTrackDetailsListener = trackDetailsListener;
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final TrackRowLayoutBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout
                .track_row_layout, parent, false);

        final TrackViewHolder trackViewHolder = new TrackViewHolder(binding);
        trackViewHolder.setListener(mOnItemClickedListener);
        return trackViewHolder;
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


    private final TrackViewHolder.OnItemClickedListener mOnItemClickedListener = new TrackViewHolder.OnItemClickedListener() {
        @Override
        public void onItemClicked(@NonNull Track track) {
            if (mTrackDetailsListener != null) mTrackDetailsListener.goToTrackDetails(track);
        }
    };

    public interface OnNavigateToTrackDetailsListener {
        void goToTrackDetails(@NonNull Track track);
    }
}
