package com.ecarx.writesd;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 模拟向SD卡写入一个视频文件
     * @param view
     */
    public void click(View view){
        performCodeWithPermission("写入文件到SD卡", new PermissionCallback() {
            @Override
            public void hasPermission() {
                // 检查SD卡状态
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    File sdFile = Environment.getExternalStorageDirectory();    //外部存储空间
                    long sdSize = sdFile.getFreeSpace();
                    if(sdSize > 5*1024*1024){   // 5M
                        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + "hlw.3gp");
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            byte[] buffer = new byte[1024];
                            for(int i=0;i<5*1024;i++){
                                fos.write(buffer);
                            }
                            fos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"sd卡空间不足",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"SD卡被拔出或者不可用",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void noPermission() {

            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE);


    }
}
