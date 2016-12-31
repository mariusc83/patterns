package org.mariusconstantin.patterns.view.playlist.interactor;

import android.support.annotation.NonNull;

import org.mariusconstantin.patterns.repo.IPlaylistRepository;
import org.mariusconstantin.patterns.repo.model.Playlist;
import org.mariusconstantin.patterns.view.di.FragmentScope;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Marius on 12/31/2016.
 */

public class PlaylistInteractor implements IPlaylistInteractor {

    @NonNull
    private final IPlaylistRepository mPlaylistRepository;

    public PlaylistInteractor(@NonNull IPlaylistRepository mPlaylistRepository) {
        this.mPlaylistRepository = mPlaylistRepository;
    }

    @Override
    public Observable<Playlist> getPlaylistObservable(long playlistId) {
        return mPlaylistRepository.getPlaylist(playlistId);
    }
}
