package com.mediaplayer.buddha.buddhamediaplayer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mediaplayer.buddha.buddhamediaplayer.fragments.LibraryAlbumFragment;
import com.mediaplayer.buddha.buddhamediaplayer.fragments.LibraryArtistFragment;
import com.mediaplayer.buddha.buddhamediaplayer.fragments.LibraryLocalFragment;

public class LibraryPagerAdapter extends FragmentStatePagerAdapter {

    public LibraryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new LibraryArtistFragment();
                break;
            case 1:
                fragment = new LibraryAlbumFragment();
                break;
            case 2:
                fragment = new LibraryLocalFragment();
                break;
            default:
                fragment = new Fragment();
                break;
        }
        return fragment;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position) {
//            case 0:
//                return "Album";
//            case 1:
//                return "Artist";
//            case 2:
//                return "Local";
//            default:
//                return "";
//        }
//    }
}
