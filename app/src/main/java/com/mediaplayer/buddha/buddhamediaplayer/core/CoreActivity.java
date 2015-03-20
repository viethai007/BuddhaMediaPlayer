package com.mediaplayer.buddha.buddhamediaplayer.core;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public abstract class CoreActivity extends ActionBarActivity {
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        setContentView(layoutId);

        init();
        loadData();
        updateUI();
        bindEvent();
    }

    protected abstract void init();

    protected abstract void loadData();

    protected abstract void updateUI();

    protected abstract void bindEvent();
}
