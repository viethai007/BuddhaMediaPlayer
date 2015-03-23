package com.mediaplayer.buddha.buddhamediaplayer.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.adapters.LibraryPagerAdapter;
import com.mediaplayer.buddha.buddhamediaplayer.core.CoreFragment;

public class LibraryFragment extends CoreFragment {
    private ViewPager pagerFragment;

    private LibraryPagerAdapter adapterFragment;

    public LibraryFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.library;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
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
        FragmentManager fm = getFragmentManager();
        adapterFragment = new LibraryPagerAdapter(fm);
        pagerFragment.setAdapter(adapterFragment);
    }

    @Override
    protected void BindEvent() {
        pagerFragment.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
