package com.ecarx.govandpeople;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class XiangGovReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("乡级部门接收到中央文件"+getResultData());
        setResultData("给每个农村户口发一袋大米，还要找记者拍照");
        System.out.println(Thread.currentThread().getName());
    }
}
