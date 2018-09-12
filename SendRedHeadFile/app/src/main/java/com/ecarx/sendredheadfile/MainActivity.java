package com.ecarx.sendredheadfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 中央发送红头文件，文件是一级一级往下传的
     * @param view
     */
    public void click(View view){
        Intent intent = new Intent();
        intent.setAction("com.ecarx.sendredheadfile.BTNM");

        ReporterReceiver receiver = new ReporterReceiver();
        sendOrderedBroadcast(intent,null,receiver,null,1,"给每个农民补贴一万块钱",null);
    }
}
