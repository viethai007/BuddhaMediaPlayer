package com.mediaplayer.buddha.buddhamediaplayer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mediaplayer.buddha.buddhamediaplayer.fragments.PlayerFragment;
import com.mediaplayer.buddha.buddhamediaplayer.fragments.PlaylistFragment;

public class NowPlayingPagerAdapter extends FragmentStatePagerAdapter {

    public NowPlayingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if(position == 0) {
            fragment = new PlayerFragment();
        }
        else {
            fragment = new PlaylistFragment();
        }
        return fragment;
    }
}
