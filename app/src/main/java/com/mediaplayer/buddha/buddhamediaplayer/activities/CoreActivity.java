package com.mediaplayer.buddha.buddhamediaplayer.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public abstract class CoreActivity extends ActionBarActivity {
    protected FragmentActivity activity;
    protected ActionBar actionBar;

    protected FragmentManager fragmentManager;

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        setContentView(layoutId);
        activity = this;
        actionBar = getSupportActionBar();
        fragmentManager = getSupportFragmentManager();

        init();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                loadData();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI();
                        bindEvent();
                    }
                });
            }
        });
    }

    protected abstract void init();

    protected abstract void loadData();

    protected abstract void updateUI();

    protected abstract void bindEvent();
}
