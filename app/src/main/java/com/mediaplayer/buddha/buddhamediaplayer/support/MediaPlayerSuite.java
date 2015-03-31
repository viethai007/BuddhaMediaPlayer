package com.mediaplayer.buddha.buddhamediaplayer.support;

import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mediaplayer.buddha.buddhamediaplayer.support.models.MediaPlayerTrack;

import java.io.IOException;
import java.util.ArrayList;

public class MediaPlayerSuite {
    private static MediaPlayerSuite instance;

    private MediaPlayer mediaPlayer;
    private TextView lblCurrentTime;
    private SeekBar seeker;
    private Runnable runnableUpdateSeekBar;
    private Handler handlerUpdateSeekBar;

    private ArrayList<MediaPlayerTrack> listTrack;
    private MediaPlayerTrack track;

    private OnMessageListener messageListener;
    private OnPlaybackStateChangeListener playbackStateChangeListener;

    private boolean __isInitialized = false;
    private boolean __playAfterTrackChange = false;
    private boolean __isSeeking = false;
    private int __currentIndex = -1;
    private int __lastPosition = 0;
    private int __seekingPosition = 0;

    private MediaPlayerSuite() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(__currentIndex > -2 && __currentIndex < listTrack.size() - 1) {
                    __playAfterTrackChange = true;
                    startNextPlayback();
                } else {
                    stopPlayback();
                }
            }
        });

        listTrack = new ArrayList<>();

        runnableUpdateSeekBar = new Runnable() {
            @Override
            public void run() {
                refreshTimeIndicator();
            }
        };
        handlerUpdateSeekBar = new Handler();
    }

    public static MediaPlayerSuite getInstance(SeekBar seeker, TextView currentTimeText) {
        if(instance == null) {
            instance = new MediaPlayerSuite();
        }
        instance.setSeekBar(seeker);
        instance.setCurrentTimeText(currentTimeText);

        return instance;
    }

    private boolean isPlaying() {
        if(mediaPlayer == null) {
            return false;
        }
        return mediaPlayer.isPlaying();
    }

    private void setCurrentProgress(int millisecond) {
        if(seeker != null) {
            int second = millisecond / 1000;
            seeker.setProgress(second);
        }
    }

    private void setCurrentTime(int millisecond) {
        if(lblCurrentTime != null) {
            int second = millisecond / 1000;
            int minute = second / 60;
            second = second % 60;
            lblCurrentTime.setText(String.format("%d:%02d", minute, second));
        }
    }

    private void setCurrentTimeText(TextView lblCurrentTime) {
        this.lblCurrentTime = lblCurrentTime;
    }

    public ArrayList<MediaPlayerTrack> getPlaylist() {
        return listTrack;
    }

    public void setPlayList(ArrayList<MediaPlayerTrack> listTrack) {
        this.listTrack = listTrack;
        this.__currentIndex = this.listTrack.indexOf(this.track);
    }

    private void setSeekBar(SeekBar seeker) {
        this.seeker = seeker;
        if(seeker != null) {
            seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(__isSeeking) {
                        __seekingPosition = progress * 1000;
                        setCurrentTime(__seekingPosition);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    __isSeeking = true;
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    __isSeeking = false;
                    mediaPlayer.seekTo(__seekingPosition);
                }
            });
        }
    }

    public void setTrack(MediaPlayerTrack trackUri) {
        this.track = trackUri;
        this.__currentIndex = this.listTrack.indexOf(this.track);
        if(isPlaying() == true) {
            __playAfterTrackChange = true;
        }

        try {
            if (__isInitialized == true) {
                mediaPlayer.reset();
                __isInitialized = false;
            }
        }
        catch (Exception e) {

        }

        try {
            mediaPlayer.setDataSource(track.Uri);
            mediaPlayer.prepare();
            __isInitialized = true;
            __lastPosition = 0;
            int second = mediaPlayer.getDuration() / 1000;
            if(seeker != null) {
                seeker.setMax(second);
            }

            onMessage("Track prepared");

            if(__playAfterTrackChange == true) {
                __playAfterTrackChange = false;
                startPlayback();
            }
        } catch (IOException e) {
            __isInitialized = false;
        }
    }

    public void startPlayback() {
        mediaPlayer.seekTo(__lastPosition);
        mediaPlayer.start();
        refreshTimeIndicator();
        track.Position = __lastPosition;
        onStartPlayback(track);
    }

    public void pausePlayback() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        track.Position = __lastPosition;
        onPausePlayback(track);
    }

    public void stopPlayback() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            __lastPosition = 0;
        }
        track.Position = __lastPosition;
        onPausePlayback(track);
    }

    public void startPrevPlayback() {
        if(__currentIndex > 0 && __currentIndex < listTrack.size() + 1) {
            MediaPlayerTrack prevTrackUri = listTrack.get(__currentIndex - 1);
            setTrack(prevTrackUri);
        }
    }

    public void startNextPlayback() {
        if(__currentIndex > -2 &&  __currentIndex < listTrack.size() - 1) {
            MediaPlayerTrack nextTrackUri = listTrack.get(__currentIndex + 1);
            setTrack(nextTrackUri);
        }
    }

    private void refreshTimeIndicator() {
        if (mediaPlayer == null || __isSeeking == true) {
            // Do nothing
        } else {
            int position = mediaPlayer.getCurrentPosition();
            setCurrentTime(position);
            setCurrentProgress(position);
        }

        if(mediaPlayer.isPlaying() == true) {
            handlerUpdateSeekBar.postDelayed(runnableUpdateSeekBar, 250);
        }
    }

    public interface OnMessageListener {
        void onMessage(String message);
    }

    public void setOnMessageListener(OnMessageListener l) {
        this.messageListener = l;
    }

    private void onMessage(String message) {
        if(messageListener != null) {
            messageListener.onMessage(message);
        }
    }

    public interface OnPlaybackStateChangeListener {
        void onStartPlayback(MediaPlayerTrack track);
        void onPausePlayback(MediaPlayerTrack track);
        void onStopPlayback(MediaPlayerTrack track);
    }

    public void setOnPlaybackStateChange(OnPlaybackStateChangeListener l) {
        this.playbackStateChangeListener = l;
    }

    private void onStartPlayback(MediaPlayerTrack track) {
        if(playbackStateChangeListener != null) {
            playbackStateChangeListener.onStartPlayback(track);
        }
    }

    private void onPausePlayback(MediaPlayerTrack track) {
        if(playbackStateChangeListener != null) {
            playbackStateChangeListener.onPausePlayback(track);
        }
    }

    private void onStopPlayback(MediaPlayerTrack track) {
        if(playbackStateChangeListener != null) {
            playbackStateChangeListener.onStopPlayback(track);
        }
    }
}
