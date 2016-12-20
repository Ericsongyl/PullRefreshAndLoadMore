package com.nicksong.pullrefresh.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nicksong.pullrefresh.R;
import com.nicksong.pullrefresh.activities.ep.DrawerEpActivity;
import com.nicksong.pullrefresh.activities.ep.NormolEpActivity;
import com.nicksong.pullrefresh.activities.ep.RG2Activity;
import com.nicksong.pullrefresh.activities.ep.RGActivity;
import com.nicksong.pullrefresh.activities.ep.ScrollerEpActivity;


/**
 * Modified by nicksong at 2016/12/20
 */
public class ExpandActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpandActivity.this, NormolEpActivity.class));
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpandActivity.this, DrawerEpActivity.class));
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpandActivity.this, ScrollerEpActivity.class));
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpandActivity.this, RGActivity.class));
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpandActivity.this, RG2Activity.class));
            }
        });
    }
}
