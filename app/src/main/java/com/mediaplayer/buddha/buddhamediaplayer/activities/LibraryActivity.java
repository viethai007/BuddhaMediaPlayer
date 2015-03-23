package com.mediaplayer.buddha.buddhamediaplayer.activities;

import android.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.adapters.LibraryPagerAdapter;
import com.mediaplayer.buddha.buddhamediaplayer.core.CoreActivity;
import com.mediaplayer.buddha.buddhamediaplayer.core.CoreMenuActivity;
import com.mediaplayer.buddha.buddhamediaplayer.fragments.LibraryFragment;

public class LibraryActivity extends CoreMenuActivity {
    private LibraryFragment fragLibrary;

    public LibraryActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
