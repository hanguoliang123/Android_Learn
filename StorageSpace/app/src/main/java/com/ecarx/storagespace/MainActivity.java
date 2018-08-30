package com.ecarx.storagespace;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取存储空间
        File dataFile = Environment.getDataDirectory(); // 数据内部存储空间
        File sdFile = Environment.getExternalStorageDirectory();//sd卡存储空间

        long dataSize = dataFile.getTotalSpace();
        long sdSize = sdFile.getTotalSpace();
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText("内部存储" + Formatter.formatFileSize(this, dataSize) + "外部存储" + Formatter.formatFileSize(this, sdSize));
    }
}
