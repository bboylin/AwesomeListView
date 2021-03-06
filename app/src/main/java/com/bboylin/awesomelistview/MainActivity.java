package com.bboylin.awesomelistview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RefreshLayout refreshableLayout;
    LoadMoreListView listView;
    ArrayAdapter<String> adapter;
    Handler mHandler;
    String[] items = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "G", "H", "I", "J", "K", "L"};
    String[] refreshItems = {"E", "F", "G", "H", "I", "J", "K", "L", "G", "H", "I", "J", "K", "L"};
    String[] loadMoreItems = {"A", "B", "C", "D", "E", "F", "G", "E", "F", "G", "H", "I", "J", "K", "L", "G", "H", "I", "J"};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        refreshableLayout = (RefreshLayout) findViewById(R.id.refreshable_layout);
        listView = (LoadMoreListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setOnPullUpLoadListener(new LoadMoreListView.OnPullUpLoadListener() {
            @Override
            public void onPullUpLoading() {
                mHandler.sendEmptyMessageDelayed(0x124, 2000);
            }
        });
        listView.setAdapter(adapter);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x123) {
                    listView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, refreshItems));
                    refreshableLayout.finishRefreshing();
                    Toast.makeText(MainActivity.this,"刷新完成",Toast.LENGTH_SHORT).show();
                } else if (msg.what == 0x124) {
                    if (i < 2) {
                        i++;
                        listView.onPullUpLoadFinished(true);
                    } else {
                        listView.onPullUpLoadFinished(false);
                        Toast.makeText(MainActivity.this,"数据加载全部完毕",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        refreshableLayout.setOnRefreshListener(new RefreshLayout.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(0x123,2000);
            }
        });
    }
}
