package com.ecarx.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 显式意图，点击按钮开启第二个界面
     * @param view
     */
    public void click01(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        this.startActivity(intent);
    }

    /**
     * 隐式意图，点击按钮开启第二个界面
     * @param view
     */
    public void click02(View view){
        Intent intent = new Intent();
        intent.setAction("com.ecarx.intent.oepn02");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("http://192.168.1.130:8080/WebServer/img"));
        startActivity(intent);
    }
}
