package com.nicksong.pullrefresh.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nicksong.pullrefresh.R;
import com.nicksong.pullrefresh.activities.other.HeaderActivity;
import com.nicksong.pullrefresh.activities.other.MIFlingActivity;


/**
 * Modified by nicksong at 2016/12/20
 */
public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherActivity.this, MIFlingActivity.class));
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherActivity.this, HeaderActivity.class));
            }
        });
    }
}
