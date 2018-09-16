package com.ecarx.bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private IService myBinder;
    private MyConn conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private  class MyConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (IService) service;
            System.out.println("屁民联系上了官员的小三");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("因为你做的太过分，已经失去了她...");
        }
    }

    /**
     * 绑定服务，调用服务里面的方法
     * @param view
     */
    public void bind(View view){
        Intent intent = new Intent(this,GuanYuanService.class);
        conn = new MyConn();
        bindService(intent, conn,BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     * @param view
     */
    public void unbind(View view){
        unbindService(conn);
        myBinder = null;
        conn = null;
    }

    /**
     * 调用服务里面的方法
     * @param view
     */
    public void call(View view){
        myBinder.callBanZheng(300);
        myBinder.去旅游();
    }

}
