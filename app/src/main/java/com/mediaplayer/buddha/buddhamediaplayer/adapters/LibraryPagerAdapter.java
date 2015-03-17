package com.mediaplayer.buddha.buddhamediaplayer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mediaplayer.buddha.buddhamediaplayer.fragments.LibraryAlbumFragment;
import com.mediaplayer.buddha.buddhamediaplayer.fragments.LibraryArtistFragment;

import java.util.ArrayList;

public class LibraryPagerAdapter extends FragmentPagerAdapter {

    public LibraryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new LibraryAlbumFragment();
                break;
            case 1:
                fragment = new LibraryArtistFragment();
                break;
            default:
                fragment = new Fragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
