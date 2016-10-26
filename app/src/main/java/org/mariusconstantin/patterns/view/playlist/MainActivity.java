package org.mariusconstantin.patterns.view.playlist;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.os.Bundle;

import org.mariusconstantin.patterns.R;
import org.mariusconstantin.patterns.databinding.ActivityMainBinding;
import org.mariusconstantin.patterns.view.BaseActivity;
import org.mariusconstantin.patterns.view.playlist.di.DaggerMainActivityComponent;
import org.mariusconstantin.patterns.view.playlist.di.MainActivityComponent;
import org.mariusconstantin.patterns.view.playlist.di.MainActivityModule;
import org.mariusconstantin.patterns.view.playlist.flowcontroller.FlowController;

import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
    private AtomicReference<MainActivityComponent> mMainActivityComponentReference = new
            AtomicReference<>();

    ActivityMainBinding mActivityMainBinding;

    @Inject
    FlowController mFlowController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getMainActivityComponent().inject(this);
        if (getSupportFragmentManager().findFragmentById(R.id.activity_main) == null) {
            mFlowController.goToPlaylist(1180358611,this);
        }
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
