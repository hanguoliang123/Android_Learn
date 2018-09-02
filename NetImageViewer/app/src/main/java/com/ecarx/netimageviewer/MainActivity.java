package com.ecarx.netimageviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private ArrayList<String> paths;
    private Handler mHandler;

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
                    //URL url = new URL("https://www.cnblogs.com/workherd/p/6421999.html");
                    //URL url = new URL("http://www.ecarx.com.cn/");
                    // 2.通过这个路径打开这个浏览器的连接
                    //HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    // 3.设置请求方式为get
                    //conn.setRequestMethod("GET");   // 注意：请求方式只能大写，不能小写
                    // 为了有一个更好的ui提醒，获取服务器的返回状态码
                    //int code = conn.getResponseCode();

                    if(200==200){  // 返回成功
                        //InputStream is = conn.getInputStream();
                        //File file = new File(getCacheDir(), "info.txt");
                        //FileOutputStream fos = new FileOutputStream(file);
                        //byte[] buffer = new byte[1024];
                        //int len = 0;
                        //while((len=is.read(buffer))!=-1){
                        //    fos.write(buffer,0,len);
                        //}
                        //is.close();
                        //fos.close();

                        beginLoadImage();

                        System.out.println("-------------- get ok --------------");
                    }else if(404==2){
                        System.out.println("-------------- get not ok --------------");
                    }else{

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * 开始加载图片，在从服务器获取完毕资源路径之后执行
     */
    private void beginLoadImage() {
        paths = new ArrayList<>();
        try {
            File file = new File(getCacheDir(), "info.txt");
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while((line=br.readLine())!=null){
                paths.add(line);
            }
            fis.close();

            loadImageByPath(paths.get(7));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadImageByPath(final String path) {
        new Thread(){
            @Override
            public void run() {
                final File file = new File(getCacheDir(), path.replace("/", "" + "jpg"));
                if(file.exists()&&file.length()>0){
                    System.out.println("通过缓存把图片获取出来...");
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    iv.setImageBitmap(bitmap);
                    Message msg = Message.obtain();
                    msg.what = 100;

                    Looper.prepare();
                    mHandler = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            Toast.makeText(MainActivity.this, "加载成功."+msg.what, Toast.LENGTH_SHORT).show();
                        }
                    };

                    mHandler.sendMessage(msg);
                    Looper.loop();
                }else{

                    Looper.prepare();
                    mHandler = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            // process incoming messages here
                            System.out.println("通过访问网络把图片资源获取出来...");
                            try {
                                URL url = new URL(path);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setRequestMethod("GET");
                                int code = conn.getResponseCode();
                                if(200==code){
                                    InputStream is = conn.getInputStream();
                                    // 内存中的图片
                                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                                    FileOutputStream fos = new FileOutputStream(file);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
                                    fos.close();
                                    is.close();

                                    iv.setImageBitmap(bitmap);
                                }else{
                                    Toast.makeText(MainActivity.this, "获取失败.", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    Looper.loop();
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
