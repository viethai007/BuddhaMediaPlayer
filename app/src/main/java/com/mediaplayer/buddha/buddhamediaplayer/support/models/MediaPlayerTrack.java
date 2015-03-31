package com.mediaplayer.buddha.buddhamediaplayer.support.models;

/**
 * Created by PRECiSN on 2015-03-31.
 */
public class MediaPlayerTrack {
    public String Uri;
    public String Title;
    public String Artist;
    public String Album;
    public int Position; // in msecond
    public int Length; // in msecond

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
