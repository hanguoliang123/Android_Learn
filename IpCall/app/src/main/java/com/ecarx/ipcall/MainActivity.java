package com.ecarx.ipcall;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private EditText et_ipnumber;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        performCodeWithPermission("往外拨打电话时获取权限", new PermissionCallback() {
            @Override
            public void hasPermission() {

            }

            @Override
            public void noPermission() {

            }
        }, Manifest.permission.PROCESS_OUTGOING_CALLS);

        et_ipnumber = (EditText) findViewById(R.id.et_ipnumber);
        sp = getSharedPreferences("config", MODE_PRIVATE);

        String ipnumber = sp.getString("ipnumber", "");
        et_ipnumber.setText(ipnumber);

    }

    public void save(View view){
        String ipnumber = et_ipnumber.getText().toString().trim();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ipnumber",ipnumber);
        editor.commit();
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }
}
