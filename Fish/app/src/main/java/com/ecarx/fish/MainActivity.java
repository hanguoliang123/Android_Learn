package com.ecarx.fish;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ecarx.alipay.IService;

public class MainActivity extends AppCompatActivity {

    private MyConn conn = null;
    private IService iService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("com.ecarx.alipay");
        conn = new MyConn();
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

    /**
     * 调用远程服务的方法，支付两元
     * @param view
     */
    public void click(View view){
        try {
            int resultCode = iService.callSafePay("zhangsan", "123", 2.00f, System.currentTimeMillis());
            switch (resultCode){
                case 200:
                    Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
                    break;
                case 404:
                    Toast.makeText(this, "银行卡余额不足", Toast.LENGTH_SHORT).show();
                    break;
                case 300:
                    Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class MyConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("成功绑定到远程alipay服务上");
            iService = IService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}
