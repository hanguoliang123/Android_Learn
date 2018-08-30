package com.ecarx.hacker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view){
        // 当读取或写入的文件是其他应用程序的目录下的子目录中的文件时，并且拥有读写权限时，当前的app不需要
        // 额外的申请权限
        File file = new File("/data/data/com.ecarx.createfile/files/readable.txt");
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            Toast.makeText(this,line,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"读取失败",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
