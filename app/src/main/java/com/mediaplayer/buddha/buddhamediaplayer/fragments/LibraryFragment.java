package com.mediaplayer.buddha.buddhamediaplayer.fragments;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediaplayer.buddha.buddhamediaplayer.R;
import com.mediaplayer.buddha.buddhamediaplayer.support.TextViewExpress;
import com.mediaplayer.buddha.buddhamediaplayer.adapters.LibraryPagerAdapter;

import java.util.ArrayList;

public class LibraryFragment extends CoreFragment {
    private ViewPager pagerFragment;
    private TextView lblArtistTab;
    private TextView lblAlbumTab;
    private TextView lblLocalTab;

    private LibraryPagerAdapter adapterFragment;

    private ArrayList<TextView> listTabIndicator;

    private TextViewExpress _TextViewExpress;

    private int __selectedTabIndex = -1;

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
        _TextViewExpress = TextViewExpress.getInstance();
        pagerFragment = (ViewPager) view.findViewById(R.id.fragment_pager);
        lblArtistTab = (TextView) view.findViewById(R.id.artist_tab);
        lblAlbumTab = (TextView) view.findViewById(R.id.album_tab);
        lblLocalTab = (TextView) view.findViewById(R.id.local_tab);
    }

    @Override
    protected void LoadData() {
        listTabIndicator = new ArrayList<TextView>();
        listTabIndicator.add(lblArtistTab);
        listTabIndicator.add(lblAlbumTab);
        listTabIndicator.add(lblLocalTab);
    }

    @Override
    protected void UpdateUI() {
        adapterFragment = new LibraryPagerAdapter(fragmentManager);
        pagerFragment.setAdapter(adapterFragment);
        setSelectedTab(0);
    }

    @Override
    protected void BindEvent() {
        pagerFragment.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                __selectedTabIndex = position;
                setSelectedTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        int tabCount = listTabIndicator.size();
        for(int i = 0; i < tabCount; i++) {
            TextView tab = listTabIndicator.get(i);
            tab.setTag(i);
            tab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = (int) v.getTag();
                    if(__selectedTabIndex != index) {
                        pagerFragment.setCurrentItem(index);
                    }
                }
            });
        }
    }

    private void setSelectedTab(int index) {
        for(TextView tab:listTabIndicator) {
             _TextViewExpress.setBackgroundResource(tab, R.drawable.tab_indicator_normal);
        }
        _TextViewExpress.setBackgroundResource(listTabIndicator.get(index), R.drawable.tab_indicator_selected);
    }
}
