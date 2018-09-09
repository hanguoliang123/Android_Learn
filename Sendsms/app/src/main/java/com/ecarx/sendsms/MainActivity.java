package com.ecarx.sendsms;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SENDTO");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("smsto:17682313701"));
        intent.putExtra("sms_body","快来使用我们的软件吧");
        startActivity(intent);
    }

}
