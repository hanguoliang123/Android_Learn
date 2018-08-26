package com.ecarx.clickme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 设置内容UI,把界面加载出来 */
        setContentView(R.layout.activity_main);

        /* 1.找到界面上的按钮 */
        Button btn_click = (Button) findViewById(R.id.button);

        /* 点击按钮的时候触发相应点击事件 */
/*        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("我被点击了");
                *//* Context是默认的上下文，代表的是吐司显示在哪个界面上 *//*
                Toast.makeText(MainActivity.this,"我被点击了",Toast.LENGTH_SHORT).show();
            }
        });*/

        //第二种点击监听实现方式
//        btn_click.setOnClickListener(new MyClickListener());
        //第三种点击监听实现方式
        btn_click.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.out.println("我被点击了");
        Toast.makeText(MainActivity.this,"我被点击了",Toast.LENGTH_SHORT).show();
    }

    //第二种点击监听实现方式
    class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            System.out.println("我被点击了");
            Toast.makeText(MainActivity.this,"我被点击了",Toast.LENGTH_SHORT).show();
        }
    }
}
