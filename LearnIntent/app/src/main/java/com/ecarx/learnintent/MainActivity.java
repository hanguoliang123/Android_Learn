package com.ecarx.learnintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnStartMyAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyAty.ACTION));   //显式Intent,直接指明了被启动的类的定义
                                                            //隐式Intent,<intent-filter>中指定
                                                            //隐式Intent优势:可以启动另一个应用的Activity,A应用不能获取到B应用的Activity的类的定义的
            }
        });

    }
}
