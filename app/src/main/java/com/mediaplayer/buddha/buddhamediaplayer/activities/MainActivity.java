package com.mediaplayer.buddha.buddhamediaplayer.activities;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.fragments.LibraryFragment;

public class MainActivity extends CoreMenuActivity {
    LibraryFragment fragmentLibrary;

    FragmentManager fm;

    @Override
    protected int getLayoutId() {
        return R.layout.menu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityType = ActivityTypeEnum.HOME;
    }

    @Override
    protected void init() {
        super.init();
        fm = getSupportFragmentManager();

    }

    @Override
    protected void loadData() {
        super.loadData();
    }

    @Override
    protected void updateUI() {
        super.updateUI();
//        Intent intent = new Intent();
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.setClass(this, LibraryActivity.class);
//        startActivity(intent);
//        FragmentTransaction transaction = fm.beginTransaction();
//        fragmentLibrary = new LibraryFragment();
//        transaction.replace(R.id.frame, fragmentLibrary);
//        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
    }
}
