package com.ecarx.netimageviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);

        // 链接服务器，获取所有图片的链接信息
        loadAllImagePath();

    }

    /**
     * 获取全部图片资源路径
     */
    private void loadAllImagePath() {

        new Thread(){
            @Override
            public void run() {

                // 浏览器发送一个get请求，就可以把服务器的数据获取出来
                // 用代码模拟一个http的get请求
                try {
                    // 1.得到服务器资源的路径
                    // http://192.168.0.2:8080/WebServer/img/gaga.html
                    URL url = new URL("https://www.cnblogs.com/workherd/p/6421999.html");
                    // 2.通过这个路径打开这个浏览器的连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    // 3.设置请求方式为get
                    conn.setRequestMethod("GET");   // 注意：请求方式只能大写，不能小写
                    // 为了有一个更好的ui提醒，获取服务器的返回状态码
                    int code = conn.getResponseCode();

                    if(200==code){  // 返回成功
                        InputStream is = conn.getInputStream();
                        File file = new File(getCacheDir(), "info.txt");
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];
                        int len = 0;
                        while((len=is.read(buffer))!=-1){
                            fos.write(buffer,0,len);
                        }
                        is.close();
                        fos.close();

                        System.out.println("-------------- get ok --------------");
                    }else if(404==code){
                        System.out.println("-------------- get not ok --------------");
                    }else{

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void pre(View view){
        loadAllImagePath();
    }

    public void next(View view){

    }
}
