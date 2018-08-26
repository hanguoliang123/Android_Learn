package com.ecarx.clickme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 设置内容UI,把界面加载出来 */
        setContentView(R.layout.activity_main);

        Button btn_click = (Button) findViewById(R.id.button);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("我被点击了");
                Toast.makeText(MainActivity.this,"我被点击了",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
