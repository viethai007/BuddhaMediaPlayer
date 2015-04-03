package com.mediaplayer.buddha.buddhamediaplayer.support;

import android.widget.TextView;

public class TextViewExpress {
    private static TextViewExpress _instance;

    public static TextViewExpress getInstance() {
        if(_instance == null) {
            _instance = new TextViewExpress();
        }
        return _instance;
    }

    public void setBackgroundResource(TextView textview, int resId) {
        int paddingLeft = textview.getPaddingLeft();
        int paddingRight = textview.getPaddingRight();
        int paddingTop = textview.getPaddingTop();
        int paddingBottom = textview.getPaddingBottom();

        textview.setBackgroundResource(resId);
        textview.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
}
