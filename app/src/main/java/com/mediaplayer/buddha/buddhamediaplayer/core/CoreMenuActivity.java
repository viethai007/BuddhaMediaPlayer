package com.mediaplayer.buddha.buddhamediaplayer.core;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.activities.LibraryActivity;
import com.mediaplayer.buddha.buddhamediaplayer.adapters.DrawerListAdapter;
import com.mediaplayer.buddha.buddhamediaplayer.models.DrawerItemModel;

import java.util.ArrayList;

public abstract class CoreMenuActivity extends CoreActivity {
    protected Activity context;
    protected DrawerLayout drawer;
    protected LinearLayout panelMenu;
    protected ListView listviewMenu;
    protected TextView buttonLibrary;
    protected TextView buttonPlaylist;
    protected TextView buttonSettings;

    protected DrawerListAdapter adapterMenu;

    protected ArrayList<DrawerItemModel> listDrawerItem;


    @Override
    protected void init() {
        context = this;
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        panelMenu = (LinearLayout) findViewById(R.id.menu_panel);
        //listviewMenu = (ListView) findViewById(R.id.menu_list);
        buttonLibrary = (TextView) findViewById(R.id.library_button);
        buttonPlaylist = (TextView) findViewById(R.id.playlist_button);
        buttonSettings = (TextView) findViewById(R.id.setting_button);
    }

    @Override
    protected void loadData() {
        listDrawerItem = new ArrayList<DrawerItemModel>();
        DrawerItemModel item1 = new DrawerItemModel();
        item1.label = "Library";
        listDrawerItem.add(item1);
        DrawerItemModel item2 = new DrawerItemModel();
        item1.label = "My Playlist";
        listDrawerItem.add(item2);
        DrawerItemModel item3 = new DrawerItemModel();
        item1.label = "Settings";
        listDrawerItem.add(item3);
    }

    @Override
    protected void updateUI() {
//        adapterMenu = new DrawerListAdapter(this, listDrawerItem);
//        listviewMenu.setAdapter(adapterMenu);
    }

    @Override
    protected void bindEvent() {
        drawer.setDrawerListener(new ActionBarDrawerToggle(context, drawer, R.string.drawer_open, R.string.drawer_close) {
             public void onDrawerClosed(View view) {
                 super.onDrawerClosed(view);
//                getActionBar().setTitle(mTitle);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
             }

             public void onDrawerOpened(View drawerView) {
                 super.onDrawerOpened(drawerView);
//                getActionBar().setTitle(mDrawerTitle);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
             }
         });

//        listviewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent();
//                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                switch (position) {
//                    case 1:
//                        break;
//                    case 2:
//                        break;
//                    default:
//                        intent.setClass(context, LibraryActivity.class);
//                        break;
//                }
//                context.startActivity(intent);
//            }
//        });

        buttonLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setClass(context, LibraryActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
