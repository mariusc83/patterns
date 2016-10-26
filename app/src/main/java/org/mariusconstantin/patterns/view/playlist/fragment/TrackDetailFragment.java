package org.mariusconstantin.patterns.view.playlist.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mariusconstantin.patterns.R;
import org.mariusconstantin.patterns.databinding.TrackDetailLayoutBinding;
import org.mariusconstantin.patterns.repo.model.Track;
import org.mariusconstantin.patterns.view.playlist.MainActivity;
import org.mariusconstantin.patterns.view.playlist.di.DaggerPlaylistComponent;
import org.mariusconstantin.patterns.view.playlist.di.MainActivityComponent;
import org.mariusconstantin.patterns.view.playlist.di.PlaylistComponent;
import org.mariusconstantin.patterns.view.playlist.di.PlaylistModule;
import org.mariusconstantin.patterns.view.playlist.model.TrackDetailViewModel;
import org.mariusconstantin.patterns.view.playlist.presenter.TrackDetailPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by MConstantin on 10/25/2016.
 */

public class TrackDetailFragment extends Fragment {

    private static final String TRACK_MODEL_KEY = "track_model";
    private static final String TAG = TrackDetailFragment.class.getSimpleName();

    @Inject
    TrackDetailPresenter mPresenter;

    private PlaylistComponent mPlaylistComponent;
    private Subscription mSubscription;
    private TrackDetailLayoutBinding mBinding;

    public static TrackDetailFragment create(@NonNull Track track) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(TRACK_MODEL_KEY, track);
        final TrackDetailFragment fragment = new TrackDetailFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.track_detail_layout, container,
                false);
        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onPause(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        final Track track = getArguments().getParcelable(TRACK_MODEL_KEY);
        mSubscription = mPresenter.onResume(mOnNext, track);
    }

    private final Action1<TrackDetailViewModel> mOnNext = new Action1<TrackDetailViewModel>() {
        @Override
        public void call(TrackDetailViewModel trackDetailViewModel) {
            mBinding.setTrackDetail(trackDetailViewModel);
        }
    };
}
