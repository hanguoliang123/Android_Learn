package com.ecarx.createfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建一个文件，这个文件可以让别的应用程序读写
        // 当创建的文件的保存路径是当前应用程序目录下的子目录时，可以不用添加权限，
        // 即使是6.0以上
        try {
            FileOutputStream fos = openFileOutput("readable.txt", MODE_WORLD_READABLE);
            fos.write("data".getBytes());
            fos.close();

            fos = openFileOutput("writeable.txt", MODE_WORLD_WRITEABLE);
            fos.write("data".getBytes());
            fos.close();

            fos = openFileOutput("public.txt", MODE_WORLD_WRITEABLE|MODE_WORLD_READABLE);
            fos.write("data".getBytes());
            fos.close();

            fos = openFileOutput("private.txt", MODE_PRIVATE);
            fos.write("data".getBytes());
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
