package com.ecarx.cctv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view){
        Toast.makeText(this, "我被点击了", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction("com.ecarx.cctv.SDSS");
        intent.putExtra("money","五毛");
        sendBroadcast(intent);


    }
}
