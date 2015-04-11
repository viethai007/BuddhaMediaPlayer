package com.mediaplayer.buddha.buddhamediaplayer.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class CoreFragment extends Fragment {
    protected FragmentActivity activity;
    protected FragmentManager fragmentManager;

    public CoreFragment() {
    }

    protected abstract int getLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        fragmentManager = getFragmentManager();
        int layoutId = getLayoutId();
        View view = inflater.inflate(layoutId, null, false);
        Init(view);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                loadData();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI();
                        bindEvent();
                    }
                });
            }
        });

        return view;
    }

    protected abstract void Init(View view);

    protected abstract void loadData();

    protected abstract void updateUI();

    protected abstract void bindEvent();
}
