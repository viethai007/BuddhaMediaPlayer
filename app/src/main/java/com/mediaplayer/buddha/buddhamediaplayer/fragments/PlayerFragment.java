package com.mediaplayer.buddha.buddhamediaplayer.fragments;


import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.support.MediaPlayerSuite;
import com.mediaplayer.buddha.buddhamediaplayer.support.models.MediaPlayerTrack;

import java.util.ArrayList;

public class PlayerFragment extends CoreFragment implements MediaPlayerSuite.OnPlaybackStateChangeListener {
    private TextView lblTrackTitle;
    private TextView lblTrackArtist;
    private ImageButton btnSwitch;
    private TextView lblTimeIndicator;
    private SeekBar skbProgress;
    private ImageButton btnPrev;
    private ImageButton btnStop;
    private ImageButton btnPause;
    private ImageButton btnPlay;
    private ImageButton btnNext;

    private MediaPlayerSuite _MediaPlayerSuite;

    private ArrayList<MediaPlayerTrack> listTrack;

    private OnSwitchListener _SwitchListener;

    public PlayerFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isVisible = getUserVisibleHint();
        if(isVisible) {
            registerEvent();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterEvent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.player;
    }


    @Override
    protected void Init(View view) {
        _MediaPlayerSuite = MediaPlayerSuite.getInstance();
        lblTrackTitle = (TextView) view.findViewById(R.id.title_text);
        lblTrackArtist = (TextView) view.findViewById(R.id.artist_text);
        btnSwitch = (ImageButton) view.findViewById(R.id.switch_button);
        skbProgress = (SeekBar) view.findViewById(R.id.seeker);
        lblTimeIndicator = (TextView) view.findViewById(R.id.time_indicator_text);
        btnPrev = (ImageButton) view.findViewById(R.id.prev_button);
        btnStop = (ImageButton) view.findViewById(R.id.stop_button);
        btnPause = (ImageButton) view.findViewById(R.id.pause_button);
        btnPlay = (ImageButton) view.findViewById(R.id.play_button);
        btnNext = (ImageButton) view.findViewById(R.id.next_button);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void updateUI() {

    }

    @Override
    protected void bindEvent() {
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSwitchPlaylistClick();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _MediaPlayerSuite.startPrevPlayback();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _MediaPlayerSuite.stopPlayback();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _MediaPlayerSuite.pausePlayback();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _MediaPlayerSuite.startPlayback();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _MediaPlayerSuite.startNextPlayback();
            }
        });
    }

    public void registerEvent() {
        _MediaPlayerSuite.setSeekBar(skbProgress);
        _MediaPlayerSuite.setTimeIndicator(lblTimeIndicator);
        _MediaPlayerSuite.setOnPlaybackStateChangeListener(this);
        setTrackInfo(_MediaPlayerSuite.getTrack());
    }

    public void unregisterEvent() {
        _MediaPlayerSuite.setSeekBar(null);
        _MediaPlayerSuite.setTimeIndicator(null);
        _MediaPlayerSuite.setOnPlaybackStateChangeListener(null);
    }

    private void setTrackInfo(MediaPlayerTrack track) {
        if(track != null && !track.isEmpty()) {
            lblTrackTitle.setText(track.Title);
            lblTrackArtist.setText(track.Artist);
        }
    }

    public void setOnSwitchListener(OnSwitchListener l) {
        this._SwitchListener = l;
    }

    @Override
    public void onSetTrack(MediaPlayerTrack track) {
        lblTrackTitle.setText(track.Title);
        lblTrackArtist.setText(track.Artist);
        skbProgress.setProgress(0);
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
        void onSwitchPlaylistClick();
    }

    private void onSwitchPlaylistClick() {
        if(_SwitchListener != null) {
            _SwitchListener.onSwitchPlaylistClick();
        }
    }
}
