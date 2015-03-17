package com.mediaplayer.buddha.buddhamediaplayer.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.core.CoreActivity;
import com.mediaplayer.buddha.buddhamediaplayer.fragments.LibraryFragment;

public class MainActivity extends CoreActivity {
    LibraryFragment fragmentLibrary;

    FragmentManager fm;

    @Override
    protected int getLayoutId() {
        return R.layout.main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void Init() {
        fm = getSupportFragmentManager();

    }

    @Override
    protected void LoadData() {

    }

    @Override
    protected void UpdateUI() {
        FragmentTransaction transaction = fm.beginTransaction();
        fragmentLibrary = new LibraryFragment();
        transaction.replace(R.id.frame, fragmentLibrary);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void BindEvent() {

    }
}
