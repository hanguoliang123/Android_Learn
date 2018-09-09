package com.ecarx.ipcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class OutCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String number = getResultData();    //获取当前的结果数据，数据就是外拨的电话号码
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        setResultData(sp.getString("ipnumber","")+number);
    }
}
