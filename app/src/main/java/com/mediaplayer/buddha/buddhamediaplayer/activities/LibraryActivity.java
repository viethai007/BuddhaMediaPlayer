package com.mediaplayer.buddha.buddhamediaplayer.activities;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.adapters.LibraryPagerAdapter;
import com.mediaplayer.buddha.buddhamediaplayer.core.CoreActivity;

public class LibraryActivity extends CoreActivity {
    private ViewPager pagerFragment;
    private PagerTitleStrip pagerTitle;

    private LibraryPagerAdapter adapterFragment;

    public LibraryActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.library;
    }

    @Override
    protected void init() {
        pagerFragment = (ViewPager) findViewById(R.id.fragment_pager);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void updateUI() {
        FragmentManager fm = getSupportFragmentManager();
        adapterFragment = new LibraryPagerAdapter(fm);
        pagerFragment.setAdapter(adapterFragment);
    }

    @Override
    protected void bindEvent() {

    }
}
