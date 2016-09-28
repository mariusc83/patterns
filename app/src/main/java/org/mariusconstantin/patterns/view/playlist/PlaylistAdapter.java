package org.mariusconstantin.patterns.view.playlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.mariusconstantin.patterns.repo.model.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MConstantin on 9/28/2016.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<TrackViewHolder> {
    private List<Track> mData;

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {

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
