package com.ecarx.smssender;

import android.Manifest;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private EditText mEt_phoneNum;
    private EditText mEt_messageCon;
    private Button btn_sendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.获取界面上的控件
        mEt_phoneNum = (EditText) findViewById(R.id.et_phoneNum);
        mEt_messageCon = (EditText) findViewById(R.id.et_messageCon);
        btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);

        btn_sendMessage.setOnClickListener(new MySendMessageListener());
        /**
         * 获取一个短信管理器，利用短信管理器发送短信
         */

    }

    private class MySendMessageListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final String phoneNum = mEt_phoneNum.getText().toString().trim();
            final String message = mEt_messageCon.getText().toString().trim();

            if("".equals(phoneNum)||"".equals(message)){
                Toast.makeText(MainActivity.this, "号码和内容不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            System.out.println(phoneNum+":"+message);

            performCodeWithPermission("发送短信权限", new PermissionCallback() {
                @Override
                public void hasPermission() {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNum,null,message,null,null);
                }

                @Override
                public void noPermission() {

                }
            }, Manifest.permission.SEND_SMS);

        }
    }
}
