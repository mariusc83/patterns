package org.mariusconstantin.patterns.view.playlist;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import org.mariusconstantin.patterns.R;
import org.mariusconstantin.patterns.databinding.ActivityMainBinding;
import org.mariusconstantin.patterns.repo.di.DaggerRepositoriesComponent;
import org.mariusconstantin.patterns.repo.di.RepositoriesComponent;
import org.mariusconstantin.patterns.repo.di.RepositoriesModule;
import org.mariusconstantin.patterns.view.BaseActivity;
import org.mariusconstantin.patterns.view.playlist.di.DaggerMainActivityComponent;
import org.mariusconstantin.patterns.view.playlist.di.MainActivityComponent;
import org.mariusconstantin.patterns.view.playlist.di.MainActivityModule;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends BaseActivity {
    private AtomicReference<MainActivityComponent> mMainActivityComponentReference = new
            AtomicReference<>();

    ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if (getSupportFragmentManager().findFragmentById(R.id.activity_main) == null) {
            switchToFragment(new PlaylistFragment());
        }
    }

    private void switchToFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_main, fragment);
        transaction.commit();
    }

    @NonNull
    public MainActivityComponent getMainActivityComponent() {
        MainActivityComponent activityComponent = mMainActivityComponentReference.get();
        if (activityComponent == null) {
            final RepositoriesComponent repositoriesComponent = DaggerRepositoriesComponent
                    .builder()
                    .applicationComponent(getApplicationComponent())
                    .repositoriesModule(new RepositoriesModule())
                    .build();
            activityComponent = DaggerMainActivityComponent
                    .builder()
                    .mainActivityModule(new MainActivityModule(this))
                    .repositoriesComponent(repositoriesComponent).build();
            mMainActivityComponentReference.compareAndSet(null, activityComponent);
        }

        return mMainActivityComponentReference.get();
    }

}
