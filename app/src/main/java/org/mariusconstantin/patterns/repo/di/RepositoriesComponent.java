package org.mariusconstantin.patterns.repo.di;

import org.mariusconstantin.patterns.di.ApplicationComponent;
import org.mariusconstantin.patterns.log.ILogger;
import org.mariusconstantin.patterns.repo.IPlaylistRepository;
import org.mariusconstantin.patterns.repo.PlaylistRepository;

import dagger.Component;

/**
 * Created by MConstantin on 9/28/2016.
 */

@RepoScope
@Component(dependencies = ApplicationComponent.class, modules = RepositoriesModule.class)
public interface RepositoriesComponent {


   IPlaylistRepository getPlaylistRepository();

   ILogger getLogger();

}
