package com.mediaplayer.buddha.buddhamediaplayer.fragments;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.adapters.NowPlayingPagerAdapter;

public class NowPlayingFragment extends CoreFragment
        implements PlayerFragment.OnSwitchListener, PlaylistFragment.OnSwitchListener {
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
    protected void loadData() {

    }

    @Override
    protected void updateUI() {
        adapterFragment = new NowPlayingPagerAdapter(fragmentManager);
        adapterFragment.setPlayerOnSwitchListener(this);
        adapterFragment.setPlaylistOnSwitchListener(this);
        pagerFragment.setAdapter(adapterFragment);
    }

    @Override
    protected void bindEvent() {
        pagerFragment.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = getActiveFragment(position);
                if (position == 0) {
                    ((PlayerFragment) fragment).registerEvent();
                } else if(position == 1){
                    ((PlaylistFragment) fragment).registerEvent();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected Fragment getActiveFragment(int position) {
        return fragmentManager.findFragmentByTag("android:switcher:" + R.id.fragment_pager + ":" + position);
    }

    @Override
    public void onSwitchPlaylistClick() {
        pagerFragment.setCurrentItem(1);
    }

    @Override
    public void onSwitchPlayerClick() {
        pagerFragment.setCurrentItem(0);
    }
}
