package com.example.lenovo.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    @Override
    public void onCreate(){
        Log.e("MyService","onCreate:=========================");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.e("MyService","onStartCommand:=========================");
        return super.onStartCommand(intent,flags,startId);
    }

    public class MyBinder extends Binder{
        public void test(){
            Log.e("MyBinder","test:===========");
        }
    }
}
