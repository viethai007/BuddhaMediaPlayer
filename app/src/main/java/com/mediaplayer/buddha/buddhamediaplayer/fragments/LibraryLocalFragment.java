package com.mediaplayer.buddha.buddhamediaplayer.fragments;


import android.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.support.adapters.PlaylistAdapter;
import com.mediaplayer.buddha.buddhamediaplayer.support.models.MediaPlayerTrack;
import com.mediaplayer.buddha.buddhamediaplayer.support.services.LocalMediaService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryLocalFragment extends CoreFragment {
    ListView listviewPlaylist;

    PlaylistAdapter adapterPlaylist;
    LocalMediaService svcLocalMedia;

    ArrayList<MediaPlayerTrack> listTrack;

    public LibraryLocalFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.library_local;
    }

    @Override
    protected void Init(View view) {
        svcLocalMedia = LocalMediaService.getInstance();
        listviewPlaylist = (ListView) view.findViewById(R.id.playlist_list);
    }

    @Override
    protected void LoadData() {
        listTrack = svcLocalMedia.getAllTrack();
    }

    @Override
    protected void UpdateUI() {
        adapterPlaylist = new PlaylistAdapter(getActivity(), 0, listTrack);
        listviewPlaylist.setAdapter(adapterPlaylist);
    }

    @Override
    protected void BindEvent() {

    }
}
