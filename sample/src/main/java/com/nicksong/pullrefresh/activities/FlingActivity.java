package com.nicksong.pullrefresh.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nicksong.pullrefresh.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Modified by nicksong at 2016/12/20
 */
public class FlingActivity extends AppCompatActivity {

    ListView listView;

    ArrayAdapter adapter;

    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fling);
        listView = (ListView) findViewById(R.id.list);
        list = getData(15);
        adapter = new ArrayAdapter(this, R.layout.item, list);

        listView.setAdapter(adapter);
    }


    int page = 1;

    private List<String> getData(int n) {
        List<String> datas = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            datas.add("第" + page + "页,第" + i + "条");
        }
        return datas;
    }
}
