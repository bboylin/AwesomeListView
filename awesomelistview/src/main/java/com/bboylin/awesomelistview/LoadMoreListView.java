package com.bboylin.awesomelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

public class LoadMoreListView extends ListView {

    public LoadMoreListView(Context context) {
        this(context, null);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public interface OnPullUpLoadListener {
        void onPullUpLoading();
    }

    public void setOnPullUpLoadListener(OnPullUpLoadListener listener) {
        mOnPullUpLoadListener = listener;
    }

    // When loading finished, the controller should call this public method to update footer view.
    public void onPullUpLoadFinished(boolean hasMoreItems) {
        // Clear flag
        mIsPullUpLoading = false;
        hideFooterView();
        ableToLoadMore = hasMoreItems;
    }

    private LoadMoreListViewFooter mFooterView;
    // The flag used to avoid loading again when the list view is already in loading state.
    private boolean mIsPullUpLoading;
    // The controller should register this listener.
    private OnPullUpLoadListener mOnPullUpLoadListener;
    // The flag used to stop loading when no data
    private boolean ableToLoadMore;

    private void init() {
        mIsPullUpLoading = false;
        ableToLoadMore = true;
        setOnScrollListener(mOnScrollListener);
    }

    private OnScrollListener mOnScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int scrollState) {
            // do nothing.
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            Log.d("xyz", "xyzonscroll");
            // Start a new loading when the last item scrolls into screen, instead of overriding method onTouchEvent.
            if (ableToLoadMore && needLoad(firstVisibleItem, visibleItemCount, totalItemCount)) {
                startPullUpLoad();
            }
        }

        private boolean needLoad(int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            int lastVisibleItem = firstVisibleItem + visibleItemCount;
            boolean isAtListEnd = lastVisibleItem == totalItemCount;
            return !mIsPullUpLoading && isAtListEnd;
        }
    };

    private void startPullUpLoad() {
        if (mOnPullUpLoadListener != null) {
            // Show the foot view and update its state to LOADING.
            showFooterView();
            mFooterView.startAnimation();
            // Set flag
            mIsPullUpLoading = true;
            // Call the callback to notify the listView's hosted controller to load data.
            mOnPullUpLoadListener.onPullUpLoading();
        }
    }

    private void showFooterView() {
        if (mFooterView == null) {
            mFooterView = new LoadMoreListViewFooter(getContext());
        }
        if (getFooterViewsCount() == 0) {
            addFooterView(mFooterView);
        }
        mFooterView.setFooterVisibility(View.VISIBLE);
    }

    // It's better to hide footer instead of removing.
    // Since after removing, we should create a new footer instance and add it into view hierarchy again,
    // this will call findViewById many times which waste time.
    private void hideFooterView() {
        if (mFooterView != null) {
            mFooterView.setFooterVisibility(View.GONE);
            mIsPullUpLoading = false;
        }
    }

    public void setAbleToLoadMore(boolean ableToLoadMore) {
        this.ableToLoadMore = ableToLoadMore;
    }
}
