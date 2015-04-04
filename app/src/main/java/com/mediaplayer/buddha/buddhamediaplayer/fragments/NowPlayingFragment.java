package com.mediaplayer.buddha.buddhamediaplayer.fragments;


import android.support.v4.view.ViewPager;
import android.view.View;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.adapters.NowPlayingPagerAdapter;

public class NowPlayingFragment extends CoreFragment {
    private ViewPager pagerFragment;

    private NowPlayingPagerAdapter adapterFragment;

    public NowPlayingFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.now_playing;
    }


    @Override
    protected void Init(View view) {
        pagerFragment = (ViewPager) view.findViewById(R.id.fragment_pager);
    }

    @Override
    protected void LoadData() {

    }

    @Override
    protected void UpdateUI() {
        adapterFragment = new NowPlayingPagerAdapter(fragmentManager);
        pagerFragment.setAdapter(adapterFragment);
    }

    @Override
    protected void BindEvent() {

    }


}
