package com.mediaplayer.buddha.buddhamediaplayer.activities;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.fragments.LibraryFragment;

public class LibraryActivity extends CoreMenuActivity {
    private LibraryFragment fragLibrary;

    public LibraryActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityType = ActivityTypeEnum.LIBRARY;
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
        fragLibrary = new LibraryFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame, fragLibrary);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
    }
}
