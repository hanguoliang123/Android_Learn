package com.ecarx.smshelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_content = (TextView) findViewById(R.id.tv_content);

    }

    /**
     * 选择短信
     * @param view
     */
    public void selectSms(View view){
        Intent intent = new Intent(this, SelectSMSActivity.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==0){
            if(data!=null){
                String text = data.getStringExtra("text");
                tv_content.setText(text);
            }else{
                tv_content.setText("没有选择短信");
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
