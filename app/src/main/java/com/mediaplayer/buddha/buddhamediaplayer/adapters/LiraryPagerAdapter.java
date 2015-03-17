package com.mediaplayer.buddha.buddhamediaplayer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class LiraryPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> listFragment;

    public LiraryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = listFragment.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
