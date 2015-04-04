package com.mediaplayer.buddha.buddhamediaplayer.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.adapters.PlaylistAdapter;
import com.mediaplayer.buddha.buddhamediaplayer.support.MediaPlayerSuite;
import com.mediaplayer.buddha.buddhamediaplayer.support.models.MediaPlayerTrack;

import java.util.ArrayList;

public class PlaylistFragment extends CoreFragment {
    private ListView lsvwTrack;

    private MediaPlayerSuite _MediaPlayerSuite;

    private ArrayList<MediaPlayerTrack> listTrack;

    private PlaylistAdapter adptTrack;

    public PlaylistFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.playlist;
    }

    @Override
    protected void Init(View view) {
        _MediaPlayerSuite = MediaPlayerSuite.getInstance();
        lsvwTrack = (ListView) view.findViewById(R.id.track_list);
    }

    @Override
    protected void LoadData() {
        listTrack = _MediaPlayerSuite.getPlaylist();
    }

    @Override
    protected void UpdateUI() {
        adptTrack = new PlaylistAdapter(getActivity(), 0, listTrack);
        lsvwTrack.setAdapter(adptTrack);
    }

    @Override
    protected void BindEvent() {

    }


}
