package org.mariusconstantin.patterns.view.playlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mariusconstantin.patterns.R;
import org.mariusconstantin.patterns.databinding.PlaylistLayoutBinding;
import org.mariusconstantin.patterns.view.playlist.di.DaggerPlaylistComponent;
import org.mariusconstantin.patterns.view.playlist.di.MainActivityComponent;
import org.mariusconstantin.patterns.view.playlist.di.PlaylistComponent;
import org.mariusconstantin.patterns.view.playlist.di.PlaylistModule;
import org.mariusconstantin.patterns.view.playlist.model.PlaylistViewModel;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;


/**
 * Created by MConstantin on 9/28/2016.
 */

public class PlaylistFragment extends Fragment implements IPlaylistContract.IPlaylistView {

    private static final String PLAYLIST_ID_KEY = "playlist_id";

    private PlaylistComponent mPlaylistComponent;

    @Inject
    IPlaylistContract.IPlaylistPresenter mPresenter;

    @Inject
    PlaylistAdapter mPlaylistAdapter;

    private PlaylistLayoutBinding mBinding;

    private Subscription mViewModelSubscription;

    public static final PlaylistFragment create(long playlistId) {
        final Bundle bundle = new Bundle();
        bundle.putLong(PLAYLIST_ID_KEY, playlistId);
        final PlaylistFragment fragment = new PlaylistFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.playlist_layout, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mBinding.recyclerView.setAdapter(mPlaylistAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModelSubscription = mPresenter.onResume(mOnNext, getArguments().getLong(PLAYLIST_ID_KEY));
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!mViewModelSubscription.isUnsubscribed()) mViewModelSubscription.unsubscribe();
    }

    private final Action1<PlaylistViewModel> mOnNext = new Action1<PlaylistViewModel>() {
        @Override
        public void call(PlaylistViewModel model) {
            mPlaylistAdapter.setData(model.tracks());
        }
    };
}
