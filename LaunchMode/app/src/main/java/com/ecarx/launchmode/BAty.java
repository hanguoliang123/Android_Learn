package com.ecarx.launchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BAty extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baty);

        tv = (TextView)findViewById(R.id.tv);
        tv.setText(String.format("TaskID:%d\nCurrent ActivityId %s",getTaskId(),toString()));

        findViewById(R.id.btnMainAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BAty.this,MainActivity.class));
            }
        });

        findViewById(R.id.btnStartBAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BAty.this,BAty.class));
            }
        });

    }
}
