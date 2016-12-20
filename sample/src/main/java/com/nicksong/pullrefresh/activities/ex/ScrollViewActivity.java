package com.nicksong.pullrefresh.activities.ex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nicksong.pullrefresh.widget.layout.BaseFooterView;
import com.nicksong.pullrefresh.widget.layout.BaseHeaderView;
import com.nicksong.pullrefresh.R;

/**
 * Modified by nicksong at 2016/12/20
 */
public class ScrollViewActivity extends AppCompatActivity implements BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener {

    BaseHeaderView headerView;
    BaseFooterView footerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);

        headerView = (BaseHeaderView) findViewById(R.id.header);
        footerView = (BaseFooterView) findViewById(R.id.footer);

        headerView.setOnRefreshListener(this);
        footerView.setOnLoadListener(this);
    }


    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        baseHeaderView.postDelayed(new Runnable() {
            @Override
            public void run() {
                headerView.stopRefresh();
            }
        }, 3000);
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        baseFooterView.postDelayed(new Runnable() {
            @Override
            public void run() {
                footerView.stopLoad();
            }
        }, 3000);
    }


}
