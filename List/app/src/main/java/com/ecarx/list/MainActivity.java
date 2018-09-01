package com.ecarx.list;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLl_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLl_container = (LinearLayout) findViewById(R.id.ll_container);

    }

    public void show(View view){
        for(int i = 0;i<50;i++){
            TextView tv = new TextView(this);
            tv.setText("我是文本"+i);
            tv.setTextColor(Color.RED);
            tv.setTextSize(20);
            mLl_container.addView(tv);
        }
    }
}
