package com.ecarx.govandpeople;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShengGovReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("省级部门接收到中央文件"+getResultData());
        setResultData("给每个农村户口发5000块钱");
    }
}
