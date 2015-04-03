package com.mediaplayer.buddha.buddhamediaplayer.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.support.adapters.DrawerListAdapter;
import com.mediaplayer.buddha.buddhamediaplayer.support.models.DrawerItemModel;

import java.util.ArrayList;

public abstract class CoreMenuActivity extends CoreActivity {
    protected Activity context;
    protected DrawerLayout drawer;
    protected LinearLayout panelMenu;
    protected TextView buttonHome;
    protected TextView buttonNowPlaying;
    protected TextView buttonLibrary;
    protected TextView buttonPlaylist;
    protected TextView buttonSettings;

    protected DrawerListAdapter adapterMenu;

    protected ArrayList<DrawerItemModel> listDrawerItem;

    protected ActivityTypeEnum activityType = ActivityTypeEnum.OTHER;

    @Override
    protected int getLayoutId() {
        return R.layout.menu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggleDrawer();
                break;
        }
        return true;
    }

    @Override
    protected void init() {
        context = this;
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        panelMenu = (LinearLayout) findViewById(R.id.menu_panel);
        buttonHome = (TextView) findViewById(R.id.home_button);
        buttonNowPlaying = (TextView) findViewById(R.id.now_playing_button);
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
        actionBar.setIcon(R.drawable.logo);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
    }

    @Override
    protected void bindEvent() {
        drawer.setDrawerListener(new ActionBarDrawerToggle(context, drawer, R.string.drawer_open, R.string.drawer_close) {
             public void onDrawerClosed(View view) {
                 super.onDrawerClosed(view);
             }

             public void onDrawerOpened(View drawerView) {
                 super.onDrawerOpened(drawerView);
             }
         });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityType == ActivityTypeEnum.HOME) {
                    return;
                }
                Intent intent = new Intent();
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        buttonNowPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityType == ActivityTypeEnum.NOW_PLAYING) {
                    return;
                }
                Intent intent = new Intent();
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setClass(context, PlayerActivity.class);
                context.startActivity(intent);
            }
        });

        buttonLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityType == ActivityTypeEnum.LIBRARY) {
                    return;
                }
                Intent intent = new Intent();
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setClass(context, LibraryActivity.class);
                context.startActivity(intent);
            }
        });
    }

    protected void openDrawer() {
        drawer.openDrawer(Gravity.START);
    }

    protected void closeDrawer() {
        drawer.closeDrawer(Gravity.START);
    }

    protected void toggleDrawer() {
        if(isDrawerOpen()) {
            closeDrawer();
        } else {
            openDrawer();
        }
    }

    protected boolean isDrawerOpen() {
        return drawer.isDrawerOpen(Gravity.START);
    }

    protected enum ActivityTypeEnum {
        HOME,
        NOW_PLAYING,
        LIBRARY,
        PLAYLIST,
        SETTING,
        OTHER
    }
}
