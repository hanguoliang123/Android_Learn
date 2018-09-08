package com.ecarx.rpcalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_result = (TextView) findViewById(R.id.tv_result);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int sex = intent.getIntExtra("sex", 0);
        byte[] result = new byte[0];
        switch (sex){
            case Sex.MALE:
                result = name.getBytes();
                break;
            case Sex.FEMALE:
                try {
                    result = name.getBytes("gbk");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Sex.UNKNOWN:
                try {
                    result = name.getBytes("iso-8859-1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                    break;
        }

        int score = 0;
        for(byte b : result){
            score += b & 0xff;
        }
        score = Math.abs(score)%100;
        tv_name.setText(name);
        tv_result.setText("人品值为："+ score);

    }
    private void showArray(byte[] arr){
        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);
        }
        System.out.println();
    }
}
