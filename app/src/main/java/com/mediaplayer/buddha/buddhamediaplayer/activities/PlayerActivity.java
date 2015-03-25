package com.mediaplayer.buddha.buddhamediaplayer.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.core.CoreMenuActivity;
import com.mediaplayer.buddha.buddhamediaplayer.fragments.PlayerFragment;

public class PlayerActivity extends CoreMenuActivity {
    PlayerFragment fragPlayer;

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void loadData() {
        super.loadData();
    }

    @Override
    protected void updateUI() {
        super.updateUI();
        fragPlayer = new PlayerFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame, fragPlayer);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
    }
}
