package com.mediaplayer.buddha.buddhamediaplayer.support.models;

public class MediaPlayerTrack {
    public String Uri;
    public String Title;
    public String Artist;
    public String Album;
    public int Position; // in msecond
    public int Length; // in msecond
    public boolean IsPlaying;

    public boolean isEmpty() {
        return (Title == null || Title == "") && (Artist == null || Artist == "");
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof MediaPlayerTrack) {
            MediaPlayerTrack other = (MediaPlayerTrack) o;
            return Uri.equals(other.Uri);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Uri.hashCode();
    }
}
