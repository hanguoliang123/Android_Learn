package com.ecarx.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class GuanYuanService extends Service {
    public GuanYuanService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("返回IBinder对象");
        return new MyBinder();
    }

    private class MyBinder extends Binder implements IService{
        public void 打麻将(){
            Toast.makeText(getApplicationContext(), "我们一起打麻将", Toast.LENGTH_SHORT).show();
        }

        public void 洗桑拿(){
            Toast.makeText(getApplicationContext(), "我们一起洗桑拿", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void callBanZheng(int money) {
            if(money>200){
                KaiGeZhengMing();
            }else{
                Toast.makeText(getApplicationContext(), "钱太少了，回家吃饭去吧", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void 去旅游() {
            System.out.println("一起去岛国旅游，你懂的...");
        }
    }

    private void KaiGeZhengMing() {
        Toast.makeText(this, "经过证明，你没有房产", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate() {
        System.out.println("::::::::onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("::::::::onDestroy");
        super.onDestroy();
    }
}
