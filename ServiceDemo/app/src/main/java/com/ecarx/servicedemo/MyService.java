package com.ecarx.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    private IBinder binder = new MyBinder();

    public MyService() {
        System.out.println("MyService");
    }

    @Override
    public IBinder onBind(Intent intent) {

        System.out.println("onBind");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("onUnbind");
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }

    public void execute(){
        System.out.println("通过binder得到Service的引用来调用Service内部的方法");
    }
}
