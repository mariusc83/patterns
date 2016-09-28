package org.mariusconstantin.patterns.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by MConstantin on 9/28/2016.
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
