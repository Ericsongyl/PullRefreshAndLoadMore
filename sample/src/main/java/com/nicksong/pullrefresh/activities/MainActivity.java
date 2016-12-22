package com.nicksong.pullrefresh.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.NestedScrollingParent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nicksong.pullrefresh.R;
import com.nicksong.pullrefresh.activities.ep.DrawerEpActivity;
import com.nicksong.pullrefresh.activities.ep.ScrollerEpActivity;
import com.nicksong.pullrefresh.activities.ex.TextViewActivity;


/**
 * Modified by nicksong at 2016/12/20
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FlingActivity.class));
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NormalRefreshActivity.class));
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DrawerEpActivity.class));
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScrollerEpActivity.class));
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TextViewActivity.class));
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NestedScrollingActivity.class));
            }
        });
    }
}
