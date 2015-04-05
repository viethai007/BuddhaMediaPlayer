package com.mediaplayer.buddha.buddhamediaplayer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mediaplayer.buddha.buddhamediaplayer.fragments.PlayerFragment;
import com.mediaplayer.buddha.buddhamediaplayer.fragments.PlaylistFragment;

public class NowPlayingPagerAdapter extends FragmentPagerAdapter {
    private PlayerFragment.OnSwitchListener _PlayerOnSwitchListener;
    private PlaylistFragment.OnSwitchListener _PlaylistOnSwitchListener;

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
            ((PlayerFragment) fragment).setOnSwitchListener(_PlayerOnSwitchListener);
        }
        else {
            fragment = new PlaylistFragment();
            ((PlaylistFragment) fragment).setOnSwitchListener(_PlaylistOnSwitchListener);
        }
        return fragment;
    }

    public void setPlayerOnSwitchListener(PlayerFragment.OnSwitchListener l) {
        _PlayerOnSwitchListener = l;
    }

    public void setPlaylistOnSwitchListener(PlaylistFragment.OnSwitchListener l) {
        _PlaylistOnSwitchListener = l;
    }
}
