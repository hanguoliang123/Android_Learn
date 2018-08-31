package com.ecarx.setting;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox cb = (CheckBox) findViewById(R.id.cb);

        /* 回显数据 */
        sp = getSharedPreferences("config", MODE_PRIVATE);

        boolean status = sp.getBoolean("status", false);
        cb.setChecked(status);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this,"setOnCheckedChangeListener",Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("status",isChecked);
                editor.commit();    //提交数据，类似关闭流 事务
                System.out.println("isChecked:"+isChecked);
            }
        });

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"setOnClickListener",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
