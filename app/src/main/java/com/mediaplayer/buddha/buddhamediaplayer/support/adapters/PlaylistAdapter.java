package com.mediaplayer.buddha.buddhamediaplayer.support.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.support.models.DrawerItemModel;
import com.mediaplayer.buddha.buddhamediaplayer.support.models.MediaPlayerTrack;

import java.util.ArrayList;

public class PlaylistAdapter extends ArrayAdapter<MediaPlayerTrack> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<MediaPlayerTrack> listTrack;

    public PlaylistAdapter(Context context, int resource, ArrayList<MediaPlayerTrack> listTrack) {
        super(context, 0);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.listTrack = listTrack;
    }

    @Override
    public int getCount() {
        return listTrack.size();
    }

    @Override
    public MediaPlayerTrack getItem(int position) {
        return listTrack.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.playlist_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.titleText = (TextView) convertView.findViewById(R.id.title_text);
            viewHolder.artistText = (TextView) convertView.findViewById(R.id.artist_text);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MediaPlayerTrack item = getItem(position);
        viewHolder.titleText.setText(item.Title);
        viewHolder.artistText.setText(item.Artist);

        return convertView;
    }

    private class ViewHolder {
        public TextView titleText;
        public TextView artistText;
    }
}
