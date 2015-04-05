package com.mediaplayer.buddha.buddhamediaplayer.support;

import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
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

    private OnMessageListener _MessageListener;
    private OnPlaybackStateChangeListener _PlaybackStateChangeListener;

    private MediaPlayerTrack __track = null;
    private boolean __is_initialized = false;
    private boolean __play_after_track_change = false;
    private boolean __is_seeking = false;
    private int __current_index = -1;
    private int __last_position = 0;
    private int __seeking_position = 0;

    private MediaPlayerSuite() {
        listTrack = new ArrayList<MediaPlayerTrack>();
        __track = new MediaPlayerTrack();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(__current_index > -2 && __current_index < listTrack.size() - 1) {
                    __play_after_track_change = true;
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

    public static MediaPlayerSuite getInstance() {
        if(instance == null) {
            instance = new MediaPlayerSuite();
        }
        return instance;
    }

    public static MediaPlayerSuite getInstance(SeekBar seeker, TextView currentTimeText) {
        if(instance == null) {
            instance = new MediaPlayerSuite();
        }
        instance.setSeekBar(seeker);
        instance.setTimeIndicator(currentTimeText);

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

    public void setTimeIndicator(TextView lblCurrentTime) {
        this.lblCurrentTime = lblCurrentTime;
    }

    public ArrayList<MediaPlayerTrack> getPlaylist() {
        return listTrack;
    }

    public void setPlayList(ArrayList<MediaPlayerTrack> listTrack) {
        this.listTrack = listTrack;
        __current_index = this.listTrack.indexOf(__track);
    }

    public void setSeekBar(SeekBar seeker) {
        this.seeker = seeker;
        if(seeker != null) {
            if(__is_initialized) {
                int second = mediaPlayer.getDuration() / 1000;
                seeker.setMax(second);
            }
            if(isPlaying()) {
                seeker.setVisibility(View.VISIBLE);
            } else {
                seeker.setVisibility(View.INVISIBLE);
            }
            seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(__is_seeking) {
                        __seeking_position = progress * 1000;
                        setCurrentTime(__seeking_position);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    __is_seeking = true;
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    __is_seeking = false;
                    mediaPlayer.seekTo(__seeking_position);
                    __last_position = __seeking_position;
                }
            });
        }
    }

    public void setTrack(MediaPlayerTrack track) {
        __track = track;
        __current_index = this.listTrack.indexOf(__track);
        if(isPlaying() == true) {
            __play_after_track_change = true;
        }

        try {
            if (__is_initialized) {
                mediaPlayer.reset();
                __is_initialized = false;
            }
        }
        catch (Exception e) {

        }

        try {
            mediaPlayer.setDataSource(__track.Uri);
            mediaPlayer.prepare();
            __is_initialized = true;
            __last_position = 0;
            int second = mediaPlayer.getDuration() / 1000;
            if(seeker != null) {
                seeker.setMax(second);
            }

            onMessage("Track prepared");
            onSetTrack(__track);
            if(__play_after_track_change) {
                __play_after_track_change = false;
                startPlayback();
            }
        } catch (IOException e) {
            __is_initialized = false;
        }
    }

    public MediaPlayerTrack getTrack() {
        return __track;
    }

    public void startPlayback() {
        if(__track.Uri == "") {
            onMessage("No Track");
            return;
        }

        if(seeker != null) {
            seeker.setVisibility(View.VISIBLE);
        }

        mediaPlayer.seekTo(__last_position);
        mediaPlayer.start();
        refreshTimeIndicator();
        __track.Position = __last_position;
        onStartPlayback(__track);
    }

    public void pausePlayback() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        __track.Position = __last_position;
        onPausePlayback(__track);
    }

    public void stopPlayback() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            __last_position = 0;
        }
        if(seeker != null) {
            seeker.setVisibility(View.INVISIBLE);
        }
        __track.Position = __last_position;
        onStopPlayback(__track);
    }

    public void startPrevPlayback() {
        if(__current_index > 0 && __current_index < listTrack.size() + 1) {
            MediaPlayerTrack prevTrackUri = listTrack.get(__current_index - 1);
            setTrack(prevTrackUri);
        }
    }

    public void startNextPlayback() {
        if(__current_index > -2 &&  __current_index < listTrack.size() - 1) {
            MediaPlayerTrack nextTrackUri = listTrack.get(__current_index + 1);
            setTrack(nextTrackUri);
        }
    }

    private void refreshTimeIndicator() {
        if (mediaPlayer == null || __is_seeking == true) {
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
        this._MessageListener = l;
    }

    private void onMessage(String message) {
        if(_MessageListener != null) {
            _MessageListener.onMessage(message);
        }
    }

    public interface OnPlaybackStateChangeListener {
        void onSetTrack(MediaPlayerTrack track);
        void onStartPlayback(MediaPlayerTrack track);
        void onPausePlayback(MediaPlayerTrack track);
        void onStopPlayback(MediaPlayerTrack track);
    }

    public void setOnPlaybackStateChangeListener(OnPlaybackStateChangeListener l) {
        this._PlaybackStateChangeListener = l;
    }

    private void onSetTrack(MediaPlayerTrack track) {
        if(_PlaybackStateChangeListener != null) {
            _PlaybackStateChangeListener.onSetTrack(track);
        }
    }

    private void onStartPlayback(MediaPlayerTrack track) {
        if(_PlaybackStateChangeListener != null) {
            _PlaybackStateChangeListener.onStartPlayback(track);
        }
    }

    private void onPausePlayback(MediaPlayerTrack track) {
        if(_PlaybackStateChangeListener != null) {
            _PlaybackStateChangeListener.onPausePlayback(track);
        }
    }

    private void onStopPlayback(MediaPlayerTrack track) {
        if(_PlaybackStateChangeListener != null) {
            _PlaybackStateChangeListener.onStopPlayback(track);
        }
    }
}
