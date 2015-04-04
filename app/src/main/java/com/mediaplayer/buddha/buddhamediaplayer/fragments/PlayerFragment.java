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
    private TextView lblTimeIndicator;
    private SeekBar skbProgress;
    private ImageButton btnPrev;
    private ImageButton btnStop;
    private ImageButton btnPlay;
    private ImageButton btnNext;

    private MediaPlayerSuite _MediaPlayerSuite;

    private ArrayList<MediaPlayerTrack> listTrack;

    public PlayerFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        _MediaPlayerSuite.setSeekBar(skbProgress);
        _MediaPlayerSuite.setTimeIndicator(lblTimeIndicator);
        _MediaPlayerSuite.setOnPlaybackStateChange(this);
        setTrackInfo(_MediaPlayerSuite.getTrack());
    }

    @Override
    public void onPause() {
        super.onPause();
        _MediaPlayerSuite.setSeekBar(null);
        _MediaPlayerSuite.setTimeIndicator(null);
        _MediaPlayerSuite.setOnPlaybackStateChange(null);
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
        skbProgress = (SeekBar) view.findViewById(R.id.seeker);
        lblTimeIndicator = (TextView) view.findViewById(R.id.time_indicator_text);
        btnPrev = (ImageButton) view.findViewById(R.id.prev_button);
        btnStop = (ImageButton) view.findViewById(R.id.stop_button);
        btnPlay = (ImageButton) view.findViewById(R.id.play_button);
        btnNext = (ImageButton) view.findViewById(R.id.next_button);
    }

    @Override
    protected void LoadData() {

    }

    @Override
    protected void UpdateUI() {

    }

    @Override
    protected void BindEvent() {
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

    private void setTrackInfo(MediaPlayerTrack track) {
        if(track != null) {
            lblTrackTitle.setText(track.Title);
            lblTrackArtist.setText(track.Artist);
        }
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
}
