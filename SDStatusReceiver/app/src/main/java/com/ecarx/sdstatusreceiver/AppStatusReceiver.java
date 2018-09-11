package com.ecarx.sdstatusreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AppStatusReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if("android.intent.action.PACKAGE_ADDED".equals(action)){
            System.out.println("应用程序被安装了."+intent.getData().toString());
        }else if("android.intent.action.PACKAGE_REMOVED".equals(action)){
            System.out.println("应用程序被卸载了."+intent.getData().toString());
        }
    }
}
