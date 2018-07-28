package com.example.zhou1.refreshrecycleview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhou1.refreshrecycleview.recycleview.RecycleviewDic;
import com.example.zhou1.refreshrecycleview.recycleview.RefreshRecycleview;
import com.example.zhou1.refreshrecycleview.recycleview.Refresh_Loadmore_Layout;
import com.example.zhou1.refreshrecycleview.recycleview.SimpleRecycleviewAdater;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    List<BaseBean> all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        // heard.setPadding(0,170,0,0);
        final Refresh_Loadmore_Layout refresh_loadmore_layout = findViewById(R.id.refresh);
        all = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            all.add(new BaseBean());
        }
        final SimpleRecycleviewAdater simpleRecycleviewAdater = new SimpleRecycleviewAdater(all, MainActivity.this);
        refresh_loadmore_layout.setAdapter(simpleRecycleviewAdater);
        refresh_loadmore_layout.setRefreshTouchEvent(new RefreshRecycleview.RefreshTouchEvent() {
            @Override
            public void onRefrshStart() {
                Log.e("refreshRecycleview", "onRefrshStart");
            }

            @Override
            public void onRefreshing() {
                Log.e("refreshRecycleview", "onRefreshing");
            }

            @Override
            public void onRefresh() {

                Log.e("refreshRecycleview", "onRefresh");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("发送关闭", "===");
                        refresh_loadmore_layout.stopRefreshOrLoadmore();
                        all.remove(0);
                        simpleRecycleviewAdater.notifyDataSetChanged();
                    }
                }, 2000);

            }

            @Override
            public void onLoadmoreStart() {

                Log.e("refreshRecycleview", "onLoadmoreStart");

            }

            @Override
            public void onLoading() {
                Log.e("refreshRecycleview", "onLoading");

            }

            @Override
            public void onLoadmore() {
                Log.e("refreshRecycleview", "onLoadmore");


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh_loadmore_layout.stopRefreshOrLoadmore();
                        all.add(new BaseBean());
                        simpleRecycleviewAdater.notifyDataSetChanged();
                    }
                }, 2000);

            }
        });

    }
}
