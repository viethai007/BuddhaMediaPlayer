package com.mediaplayer.buddha.buddhamediaplayer.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.fragments.NowPlayingFragment;
import com.mediaplayer.buddha.buddhamediaplayer.fragments.PlayerFragment;

public class NowPlayingActivity extends CoreMenuActivity {
    NowPlayingFragment fragNowPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityType = ActivityTypeEnum.NOW_PLAYING;
    }

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
        fragNowPlaying = new NowPlayingFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame, fragNowPlaying);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
    }
}
