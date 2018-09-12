package com.ecarx.sendredheadfile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @version $Rev$
 * @authod:Guoliang.Han
 * @des ${TODO}
 * @updateAuthor $authod$
 * @updateDes ${TODO}
 */
public class ReporterReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("我是记者，我在农村里面获取到了真实的信息："+getResultData());
    }
}
