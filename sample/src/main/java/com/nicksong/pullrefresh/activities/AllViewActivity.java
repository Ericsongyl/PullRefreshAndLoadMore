package com.nicksong.pullrefresh.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nicksong.pullrefresh.R;
import com.nicksong.pullrefresh.activities.ex.RecyclerViewActivity;
import com.nicksong.pullrefresh.activities.ex.ScrollViewActivity;
import com.nicksong.pullrefresh.activities.ex.TextViewActivity;


/**
 * Modified by nicksong at 2016/12/20
 */
public class AllViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_view);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllViewActivity.this, ScrollViewActivity.class));
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllViewActivity.this, TextViewActivity.class));
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllViewActivity.this, RecyclerViewActivity.class));
            }
        });
    }
}
