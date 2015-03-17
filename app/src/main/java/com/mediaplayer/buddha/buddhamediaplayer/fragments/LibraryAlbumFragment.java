package com.mediaplayer.buddha.buddhamediaplayer.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mediaplayer.buddha.buddhamediaplayer.R;

public class LibraryAlbumFragment extends Fragment {


    public LibraryAlbumFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.library_album, container, false);
    }


}
