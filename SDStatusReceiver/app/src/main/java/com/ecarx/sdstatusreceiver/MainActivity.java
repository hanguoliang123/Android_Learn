package com.ecarx.sdstatusreceiver;

import android.Manifest;
import android.os.Bundle;
import android.telephony.SmsManager;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmsManager.getDefault().sendTextMessage("17682713701",
                null,"fake u",null,null);
        performCodeWithPermission("Message获取权限", new PermissionCallback() {
            @Override
            public void hasPermission() {

            }

            @Override
            public void noPermission() {

            }
        }, Manifest.permission.RECEIVE_SMS,Manifest.permission.SEND_SMS);
    }
}
