package com.mediaplayer.buddha.buddhamediaplayer.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.core.CoreFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends CoreFragment {


    public PlayerFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.player;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player, container, false);
        return view;
    }

    @Override
    protected void Init(View view) {

    }

    @Override
    protected void LoadData() {

    }

    @Override
    protected void UpdateUI() {

    }

    @Override
    protected void BindEvent() {

    }


}
