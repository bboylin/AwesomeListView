package com.bboylin.awesomelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoadMoreListViewFooter extends LinearLayout {

    public LoadMoreListViewFooter(Context context) {
        this(context, null);
    }

    public LoadMoreListViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private RelativeLayout mLoadingLayout;
    private TextView mLoadingLabel;
    private ImageView mLoadingImage;

    private void init() {
        inflate(getContext(), R.layout.load_more_footer, this);
        mLoadingLayout = (RelativeLayout) findViewById(R.id.load_more_layout);
        mLoadingLabel = (TextView) findViewById(R.id.description);
        mLoadingImage = (ImageView) findViewById(R.id.imageview_loading);
    }

    public void setFooterVisibility(int visibility){
        this.setVisibility(visibility);
        mLoadingImage.setVisibility(visibility);
        mLoadingLabel.setVisibility(visibility);
        mLoadingLayout.setVisibility(visibility);
    }

    /**
     * 根据当前的状态来旋转进度圈。
     */
    public void startAnimation() {
        ImageUtils.startAnimation(mLoadingImage);
    }
}
