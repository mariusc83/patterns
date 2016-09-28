package org.mariusconstantin.patterns.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import org.mariusconstantin.patterns.PatternsApplication;
import org.mariusconstantin.patterns.di.ApplicationComponent;


/**
 * Created by MConstantin on 9/28/2016.
 */

public class BaseActivity extends AppCompatActivity {


    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return PatternsApplication.getInstance().getApplicationComponent();
    }
}
