package org.mariusconstantin.patterns.repo.di;

import org.mariusconstantin.patterns.log.ILogger;
import org.mariusconstantin.patterns.repo.IPlaylistRepository;
import dagger.Subcomponent;

/**
 * Created by MConstantin on 9/28/2016.
 */

@RepoScope
@Subcomponent(modules = RepositoriesModule.class)
public interface RepositoriesSubcomponent {


    IPlaylistRepository getPlaylistRepository();

    ILogger getLogger();

    @Subcomponent.Builder
    interface Builder {
        Builder repositoriesModule(RepositoriesModule module);

        RepositoriesSubcomponent build();
    }

}
