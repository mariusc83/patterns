package org.mariusconstantin.patterns.repo.di;

import org.mariusconstantin.patterns.network.IApiService;
import org.mariusconstantin.patterns.repo.IPlaylistRepository;
import org.mariusconstantin.patterns.repo.LocalPlaylistRepository;
import org.mariusconstantin.patterns.repo.NetworkPlaylistRepository;
import org.mariusconstantin.patterns.repo.PlaylistRepository;
import org.mariusconstantin.patterns.repo.di.qualifiers.LocalPlaylistRepo;
import org.mariusconstantin.patterns.repo.di.qualifiers.NetworkPlaylistRepo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MConstantin on 9/28/2016.
 */

@Module
public class RepositoriesModule {

    @LocalPlaylistRepo
    @Provides
    public IPlaylistRepository provideLocalPlaylistRepository() {
        return new LocalPlaylistRepository();
    }

    @NetworkPlaylistRepo
    @Provides
    public IPlaylistRepository provideNetworkPlaylistRepository(IApiService apiService) {
        return new NetworkPlaylistRepository(apiService);
    }

    @RepoScope
    @Provides
    public IPlaylistRepository providePlaylistRepository(@LocalPlaylistRepo IPlaylistRepository
                                                                localRepository,
                                                        @NetworkPlaylistRepo IPlaylistRepository
                                                                networkRepository) {
        return new PlaylistRepository(networkRepository, localRepository);
    }
}
