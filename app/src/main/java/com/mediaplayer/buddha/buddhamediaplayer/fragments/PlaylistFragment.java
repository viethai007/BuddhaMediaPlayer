package com.mediaplayer.buddha.buddhamediaplayer.fragments;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.adapters.PlaylistAdapter;
import com.mediaplayer.buddha.buddhamediaplayer.support.MediaPlayerSuite;
import com.mediaplayer.buddha.buddhamediaplayer.support.models.MediaPlayerTrack;

import java.util.ArrayList;

public class PlaylistFragment extends CoreFragment implements MediaPlayerSuite.OnPlaybackStateChangeListener {
    private ImageButton btnSwitch;
    private ListView lsvwTrack;

    private MediaPlayerSuite _MediaPlayerSuite;

    private ArrayList<MediaPlayerTrack> listTrack;

    private PlaylistAdapter adptTrack;

    private OnSwitchListener _SwitchListener;

    public PlaylistFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isVisible = getUserVisibleHint();
        if(isVisible) {
            _MediaPlayerSuite.setOnPlaybackStateChangeListener(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        _MediaPlayerSuite.setOnPlaybackStateChangeListener(null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.playlist;
    }

    @Override
    protected void Init(View view) {
        _MediaPlayerSuite = MediaPlayerSuite.getInstance();
        btnSwitch = (ImageButton) view.findViewById(R.id.switch_button);
        lsvwTrack = (ListView) view.findViewById(R.id.track_list);
    }

    @Override
    protected void loadData() {
        listTrack = _MediaPlayerSuite.getPlaylist();
    }

    @Override
    protected void updateUI() {
        adptTrack = new PlaylistAdapter(getActivity(), 0, listTrack);
        lsvwTrack.setAdapter(adptTrack);
    }

    @Override
    protected void bindEvent() {
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSwitchPlayerClick();
            }
        });

        lsvwTrack.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaPlayerTrack _track = adptTrack.getItem(position);
                _MediaPlayerSuite.setTrack(_track);
            }
        });
    }

    public void setSelectedTrack(MediaPlayerTrack track) {
        adptTrack.setSelectedItem(track.Uri);
        int firstPosition = lsvwTrack.getFirstVisiblePosition();
        int lastPosition = lsvwTrack.getLastVisiblePosition();
        for(int i = firstPosition; i <= lastPosition; i++) {
            View _view = lsvwTrack.getChildAt(i);
            MediaPlayerTrack _track = adptTrack.getItem(i);
            if(_view != null) {
                adptTrack.setViewParam(_view, _track);
            }
        }
    }

    public void registerEvent() {
        _MediaPlayerSuite.setOnPlaybackStateChangeListener(this);
        setSelectedTrack(_MediaPlayerSuite.getTrack());
    }

    public void unregisterEvent() {
        _MediaPlayerSuite.setOnPlaybackStateChangeListener(null);
    }

    public void setOnSwitchListener(OnSwitchListener l) {
        this._SwitchListener = l;
    }

    @Override
    public void onSetTrack(MediaPlayerTrack track) {
        setSelectedTrack(track);
    }

    @Override
    public void onStartPlayback(MediaPlayerTrack track) {

    }

    @Override
    public void onPausePlayback(MediaPlayerTrack track) {

    }

    @Override
    public void onStopPlayback(MediaPlayerTrack track) {

    }

    public interface OnSwitchListener {
        void onSwitchPlayerClick();
    }

    private void onSwitchPlayerClick() {
        if(_SwitchListener != null) {
            _SwitchListener.onSwitchPlayerClick();
        }
    }
}
