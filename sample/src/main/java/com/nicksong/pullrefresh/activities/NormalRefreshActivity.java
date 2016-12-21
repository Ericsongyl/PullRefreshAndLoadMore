package com.nicksong.pullrefresh.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nicksong.pullrefresh.widget.layout.BaseFooterView;
import com.nicksong.pullrefresh.widget.layout.BaseHeaderView;
import com.nicksong.pullrefresh.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Modified by nicksong at 2016/12/20
 */
public class NormalRefreshActivity extends AppCompatActivity implements BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener {

    ListView listView;
    BaseHeaderView headerView;
    BaseFooterView footerView;

    ArrayAdapter adapter;

    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_refresh);

        listView = (ListView) findViewById(R.id.list);
        headerView = (BaseHeaderView) findViewById(R.id.header);
        footerView = (BaseFooterView) findViewById(R.id.footer);

        list = getData(5);

        adapter = new ArrayAdapter(this, R.layout.item, list);

        listView.setAdapter(adapter);

        headerView.setOnRefreshListener(this);
        footerView.setOnLoadListener(this);
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        baseHeaderView.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                List<String> datas = getData(5);
                list.clear();
                list.addAll(datas);
                adapter.notifyDataSetChanged();
                headerView.stopRefresh();
            }
        }, 3000);
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        baseFooterView.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                List<String> datas = getData(5);
                list.addAll(datas);
                adapter.notifyDataSetChanged();
                footerView.stopLoad();
            }
        }, 3000);
    }


    int page = 1;

    private List<String> getData(int n) {
        List<String> datas = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            datas.add(page + "--" + i);
        }
        return datas;
    }
}

