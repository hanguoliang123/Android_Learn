package com.ecarx.sendargs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnStartAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,TheAty.class);
                //i.putExtra("data","Hello sdssgl.");
                //传递数据包
                Bundle b = new Bundle();
                b.putString("name","sdssgl---bundle.");
                b.putInt("age",2);
                b.putString("name1","sdssgl-name1.");

                i.putExtra("data",b);
                //i.putExtras(b);
                startActivity(i);
            }
        });
    }
}
