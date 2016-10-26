package org.mariusconstantin.patterns.view.playlist.di;

import org.mariusconstantin.patterns.view.di.FragmentScope;
import org.mariusconstantin.patterns.view.playlist.IPlaylistContract;
import org.mariusconstantin.patterns.view.playlist.fragment.PlaylistFragment;
import org.mariusconstantin.patterns.view.playlist.fragment.TrackDetailFragment;

import dagger.Component;

/**
 * Created by MConstantin on 9/28/2016.
 */

@Component(dependencies = MainActivityComponent.class, modules = PlaylistModule.class)
@FragmentScope
public interface PlaylistComponent {

    void inject (TrackDetailFragment trackDetailFragment);

    void inject(PlaylistFragment playlistFragment);
}
