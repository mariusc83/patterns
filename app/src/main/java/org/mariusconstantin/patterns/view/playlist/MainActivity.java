package org.mariusconstantin.patterns.view.playlist;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import org.mariusconstantin.patterns.R;
import org.mariusconstantin.patterns.databinding.ActivityMainBinding;
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
            switchToFragment(PlaylistFragment.create(1180358611));
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
            activityComponent = DaggerMainActivityComponent
                    .builder()
                    .applicationComponent(getApplicationComponent())
                    .mainActivityModule(new MainActivityModule(this)).build();
            mMainActivityComponentReference.compareAndSet(null, activityComponent);
        }

        return mMainActivityComponentReference.get();
    }

}
