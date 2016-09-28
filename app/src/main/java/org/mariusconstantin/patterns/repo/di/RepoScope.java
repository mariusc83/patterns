package org.mariusconstantin.patterns.repo.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by MConstantin on 9/28/2016.
 * Used to scope the dependencies provided by the {@link RepositoriesModule}
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RepoScope {
}
