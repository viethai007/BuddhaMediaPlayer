package com.mediaplayer.buddha.buddhamediaplayer.core;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.mediaplayer.buddha.buddhamediaplayer.R;

public abstract class CoreActivity extends ActionBarActivity {
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Init();
        LoadData();
        UpdateUI();
        BindEvent();
        int layoutId = getLayoutId();
        setContentView(layoutId);
    }

    protected abstract void Init();

    protected abstract void LoadData();

    protected abstract void UpdateUI();

    protected abstract void BindEvent();
}
