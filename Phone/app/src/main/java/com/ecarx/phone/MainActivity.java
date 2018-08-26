package com.ecarx.phone;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEt_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_main);
        //寻找关心的控件
        mEt_phone = (EditText) findViewById(R.id.et_phone);
        Button btn_dail = (Button) findViewById(R.id.btn_dail);
        btn_dail.setOnClickListener(new MyClassListener());
    }
    private class MyClassListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //当用户点击按钮的时候获取里面的电话号码
            String phone = mEt_phone.getText().toString().trim();

            if("".equals(phone)){
                Toast.makeText(MainActivity.this,"电话号码不能为空",Toast.LENGTH_SHORT).show();
            }else{
                // 1.创建一个意图对象,
                Intent intent = new Intent();
                // 2.设置动作
                intent.setAction(Intent.ACTION_CALL);
                // 3.指定动作的数据
                intent.setData(Uri.parse("tel://" + phone));
                // 4.开启一个界面，根据程序员指定的行为来做事情
                startActivity(intent);

            }
        }
    }
}
