package com.example.lenovo.myapplication;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //当Service绑定成功，并且方法参数中的"IBinder service"对象非空时调用,
            //该参数的值是通过Servcie里面的onBind()方法返回
            Log.e("SecondActiviy","onServiceConnected:=========");

            //当Service绑定成功，并且方法参数中的"IBinder service"对象非空时调用,
            //该参数的值是通过Service里面的onBind方法返回
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            myBinder.test();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //当Service所在进程被杀死，Service连接断开时调用此方法
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = new Intent();
        //设置要绑定的Service
        intent.setClass(this,MyService.class);
        //绑定Service需要三个参数
        //参数1：intent对象
        //参数2：ServiceConnection对象
        //参数3：Context.BIND_AUTO_CREATE表示如果绑定的Service还未创建，则自动创建一个
        bindService(intent,conn,Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //在界面销毁的时候，需要调用unbindService()方法解绑
        unbindService(conn);
    }
}
