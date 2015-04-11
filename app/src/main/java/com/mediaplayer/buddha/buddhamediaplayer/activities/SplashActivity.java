package com.mediaplayer.buddha.buddhamediaplayer.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.mediaplayer.buddha.buddhamediaplayer.R;

public class SplashActivity extends FragmentActivity {
    private SplashActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                // TODO: perform pre-app config
                Intent intent = new Intent();
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setClass(activity, LibraryActivity.class);
                activity.startActivity(intent);
            }
        }, 1000);

    }
}
