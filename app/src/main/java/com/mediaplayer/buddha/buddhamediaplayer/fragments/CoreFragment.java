package com.mediaplayer.buddha.buddhamediaplayer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediaplayer.buddha.buddhamediaplayer.R;

public abstract class CoreFragment extends Fragment {

    public CoreFragment() {
    }

    protected abstract int getLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        View view = inflater.inflate(layoutId, null, false);
        Init(view);
        LoadData();
        UpdateUI();
        BindEvent();
        return view;
    }

    protected abstract void Init(View view);

    protected abstract void LoadData();

    protected abstract void UpdateUI();

    protected abstract void BindEvent();
}
