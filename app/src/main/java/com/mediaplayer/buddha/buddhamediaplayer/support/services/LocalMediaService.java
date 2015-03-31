package com.mediaplayer.buddha.buddhamediaplayer.support.services;

import android.media.MediaMetadataRetriever;
import android.os.Environment;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.support.MediaPlayerSuite;
import com.mediaplayer.buddha.buddhamediaplayer.support.RX;
import com.mediaplayer.buddha.buddhamediaplayer.support.models.MediaPlayerTrack;

import java.io.File;
import java.util.ArrayList;

public class LocalMediaService {
    private static LocalMediaService _instance;
    private MediaMetadataRetriever mediaMetadataRetriever;

    private ArrayList<String> listAllowedExtension;

    private LocalMediaService() {
        mediaMetadataRetriever = new MediaMetadataRetriever();
        listAllowedExtension = RX.Array.AllowedExtension;
    }

    public static LocalMediaService getInstance() {
        if(_instance == null) {
            _instance = new LocalMediaService();
        }
        return _instance;
    }

    public ArrayList<MediaPlayerTrack> getAllTrack() {
        ArrayList<MediaPlayerTrack> result = new ArrayList<MediaPlayerTrack>();
        File musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        String[] arrayMediaFile = musicDir.list();
        for(String mediaFile:arrayMediaFile) {
            if(!checkFileName(mediaFile)) {
                continue;
            }
            MediaPlayerTrack track = readMeta(mediaFile);
            result.add(track);
        }
        return result;
    }

    private MediaPlayerTrack readMeta(String fullPath) {
        MediaPlayerTrack result = new MediaPlayerTrack();
        mediaMetadataRetriever.setDataSource(fullPath);
        result.Title = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        result.Artist = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        result.Album = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        mediaMetadataRetriever.release();
        return result;
    }

    private boolean checkFileName(String fullPath) {
        for(String ext:listAllowedExtension) {
            if(fullPath.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}
