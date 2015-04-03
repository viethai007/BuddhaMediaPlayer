package com.mediaplayer.buddha.buddhamediaplayer.support.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.support.models.DrawerItemModel;

import java.util.ArrayList;

public class DrawerListAdapter extends ArrayAdapter<DrawerItemModel> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<DrawerItemModel> listDrawerItem;

    public DrawerListAdapter(Context context, ArrayList<DrawerItemModel> listDrawerItem) {
        super(context, 0);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.listDrawerItem = listDrawerItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.drawer_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageIcon = (ImageView) convertView.findViewById(R.id.icon_image);
            viewHolder.textLabel = (TextView) convertView.findViewById(R.id.label_text);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DrawerItemModel item = getItem(position);
        viewHolder.textLabel.setText(item.label);

        return convertView;
    }

    private class ViewHolder {
        public ImageView imageIcon;
        public TextView textLabel;
    }
}
