package org.mariusconstantin.patterns.view.playlist.flowcontroller;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.mariusconstantin.patterns.R;
import org.mariusconstantin.patterns.repo.model.Track;
import org.mariusconstantin.patterns.view.di.ActivityScope;
import org.mariusconstantin.patterns.view.playlist.fragment.PlaylistFragment;
import org.mariusconstantin.patterns.view.playlist.fragment.TrackDetailFragment;

import javax.inject.Inject;

/**
 * Created by MConstantin on 10/26/2016.
 */

@ActivityScope
public class FlowController {


    @Inject
    public FlowController() {
    }

    public void goToTrackDetails(@NonNull Track track, FragmentActivity fragmentActivity) {
        switchToFragment(TrackDetailFragment.create(track), fragmentActivity.getSupportFragmentManager());
    }

    public void goToPlaylist(long playlistId, FragmentActivity fragmentActivity) {
        switchToFragment(PlaylistFragment.create(playlistId), fragmentActivity.getSupportFragmentManager());
    }

    private void switchToFragment(Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        final Fragment prevFragment = fragmentManager.findFragmentById(R.id.activity_main);
        if (prevFragment != null) {
            transaction.replace(R.id.activity_main, fragment);
        } else {
            transaction.add(R.id.activity_main, fragment);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
