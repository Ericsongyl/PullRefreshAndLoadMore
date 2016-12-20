package com.nicksong.pullrefresh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nicksong.pullrefresh.widget.layout.BaseFooterView;
import com.nicksong.pullrefresh.widget.layout.BaseHeaderView;
import com.nicksong.pullrefresh.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment implements BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener {
    View view;

    BaseHeaderView headerView;
    BaseFooterView footerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3, container, false);

        headerView = findViewById(R.id.header);
        footerView = findViewById(R.id.footer);

        headerView.setOnRefreshListener(this);
        footerView.setOnLoadListener(this);
        return view;
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


    public <T> T findViewById(int id) {
        return (T) view.findViewById(id);

    }
}