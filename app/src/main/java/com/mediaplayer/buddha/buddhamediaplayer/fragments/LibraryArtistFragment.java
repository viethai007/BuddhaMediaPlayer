package com.mediaplayer.buddha.buddhamediaplayer.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mediaplayer.buddha.buddhamediaplayer.R;

public class LibraryArtistFragment extends Fragment {

    public LibraryArtistFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.library_artist, container, false);
    }
}
