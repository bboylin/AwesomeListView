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

    private RelativeLayout mLoadingLinearLayout;
    private TextView mLoadingLabel;
    private ImageView mLoadmoreImage;

    private void init() {
        inflate(getContext(), R.layout.load_more_footer, this);
        mLoadingLinearLayout = (RelativeLayout) findViewById(R.id.load_more_layout);
        mLoadingLabel = (TextView) findViewById(R.id.description);
        mLoadmoreImage = (ImageView) findViewById(R.id.imageview_loading);
    }

    /**
     * 根据当前的状态来旋转进度圈。
     */
    public void startAnimation() {
        ImageUtils.startAnimation(mLoadmoreImage);
    }
}
