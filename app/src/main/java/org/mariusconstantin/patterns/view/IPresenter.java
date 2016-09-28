package org.mariusconstantin.patterns.view;

import android.os.Bundle;

/**
 * Created by MConstantin on 9/28/2016.
 */

public interface IPresenter {

    void onCreate(Bundle savedInstanceState);

    void onPause(Bundle savedInstanceState);

}
