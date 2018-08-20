package com.example.lenovo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button =  (Button)findViewById(R.id.main_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,MyService.class);
                intent.putExtra("name","sdssgl");
                startService(intent);
                /// /startActivity(intent);
                //startActivityForResult(intent,0);
            }
        });

        Button button1 =  (Button)findViewById(R.id.main_button2);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SecondActivity.class);
                //intent.putExtra("name","sdssgl");
                //startService(intent);
                startActivity(intent);
                //startActivityForResult(intent,0);
            }
        });

        findViewById(R.id.main_button1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //实例化Intent对象
                Intent intent = new Intent();
                //指定要停止的Service
                intent.setClass(MainActivity.this,MyService.class);
                //停止Service
                stopService(intent);
            }
        });
        findViewById(R.id.main_button3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //实例化Intent对象
                Intent intent = new Intent();
                //指定要停止的Service
                intent.setClass(MainActivity.this,MyIntentService.class);
                //停止Service
                Log.e("MyIntentService","start Service:========");
                startService(intent);
            }
        });
    }

    //该方法用于接收通过startActivityForResult方法
    @Override
    protected void onActivityResult(int requestCode,int reSultCode,Intent data){
        super.onActivityResult(requestCode,requestCode,data);
        if(0==requestCode){
            if(1==reSultCode){
                String result = data.getStringExtra("结果");
                Log.e("result","sdssgl-onActivityResult:result========"+result);
            }
        }
    }

}
