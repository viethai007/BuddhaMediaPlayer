package com.mediaplayer.buddha.buddhamediaplayer.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.mediaplayer.buddha.buddhamediaplayer.R;

public class HeightScaleImageView extends ImageView {
    protected int ratioWidth;
    protected int ratioHeight;

    public void setRatio(int width, int height) {
        ratioWidth = width;
        ratioHeight = height;
    }

    protected boolean isOverriden() {
        return ratioWidth > 0 && ratioHeight > 0;
    }

    public HeightScaleImageView(Context context) {
        super(context);
    }

    public HeightScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        processCustomAttrs(context, attrs);
    }

    public HeightScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        processCustomAttrs(context, attrs);
    }

    private void processCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray arrAttribute = context.obtainStyledAttributes(attrs, R.styleable.ImageRatio);
        int ratioState = 0;
        int attrCount = arrAttribute.getIndexCount();
        for(int i = 0; i < attrCount; i++) {
            int attribute = arrAttribute.getIndex(i);
            switch(attribute) {
                case R.styleable.ImageRatio_ratioWidth:
                    ratioWidth = arrAttribute.getInt(attribute, 0);
                    break;
                case R.styleable.ImageRatio_ratioHeight:
                    ratioHeight = arrAttribute.getInt(attribute, 0);
                    break;
                default:
                    break;
            }
        }

        arrAttribute.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(isOverriden()) {
            int width = getMeasuredWidth();
            int height = (ratioHeight * width) / ratioWidth;
            super.onMeasure(width, height);
        }
        else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
