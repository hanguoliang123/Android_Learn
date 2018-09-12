package com.ecarx.govandpeople;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NongMinReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("农民接收到中央文件，"+getResultData());
    }
}
