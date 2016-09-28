package org.mariusconstantin.patterns.view.playlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mariusconstantin.patterns.R;
import org.mariusconstantin.patterns.databinding.PlaylistLayoutBinding;
import org.mariusconstantin.patterns.view.playlist.di.DaggerPlaylistComponent;
import org.mariusconstantin.patterns.view.playlist.di.MainActivityComponent;
import org.mariusconstantin.patterns.view.playlist.di.PlaylistComponent;
import org.mariusconstantin.patterns.view.playlist.di.PlaylistModule;

import javax.inject.Inject;


/**
 * Created by MConstantin on 9/28/2016.
 */

public class PlaylistFragment extends Fragment implements IPlaylistContract.IPlaylistView {

    private PlaylistComponent mPlaylistComponent;

    @Inject IPlaylistContract.IPlaylistPresenter mPresenter;


    private PlaylistLayoutBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MainActivityComponent activityComponent = ((MainActivity) getActivity())
                .getMainActivityComponent();

        mPlaylistComponent = DaggerPlaylistComponent.builder()
                .mainActivityComponent(activityComponent)
                .playlistModule(new PlaylistModule())
                .build();

       mPlaylistComponent.inject(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater, R.layout.playlist_layout,container,false);
        return mBinding.getRoot();
    }
}
