package com.ecarx.netimageviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
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

    private static final int LOAD_IMAGE = 1;
    private static final int LOAD_ERR   = 2;
    private ImageView iv;
    private ArrayList<String> paths;
    private int currentPosition = 0;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case LOAD_IMAGE:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    iv.setImageBitmap(bitmap);
                    Toast.makeText(MainActivity.this, "图片加载成功.", Toast.LENGTH_SHORT).show();
                    break;
                case LOAD_ERR:
                    Toast.makeText(MainActivity.this, "图片加载失败.", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            return false;
        }
    });

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

        new Thread() {
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

                    if (200 == 200) {  // 返回成功
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
                    } else if (404 == 2) {
                        Message msg = Message.obtain();
                        msg.what = LOAD_ERR;
                        msg.obj = "获取html文件失败,返回码:";
                        handler.sendMessage(msg);
                        //Toast.makeText(MainActivity.this, "获取失败.", Toast.LENGTH_SHORT).show();
                    } else {
                        Message msg = Message.obtain();
                        msg.what = LOAD_ERR;
                        msg.obj = "服务器异常";
                        handler.sendMessage(msg);
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
            while ((line = br.readLine()) != null) {
                paths.add(line);
            }
            fis.close();

            loadImageByPath(paths.get(currentPosition));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadImageByPath(final String path) {
        new Thread() {
            @Override
            public void run() {
                final File file = new File(getCacheDir(), path.replace("/", "" + "jpg"));
                if (file.exists() && file.length() > 0) {
                    System.out.println("通过缓存把图片获取出来...");
                    Message msg = Message.obtain();
                    msg.what = LOAD_IMAGE;
                    msg.obj = BitmapFactory.decodeFile(file.getAbsolutePath());
                    handler.sendMessage(msg);
                } else {

                    // process incoming messages here
                    System.out.println("通过访问网络把图片资源获取出来...");
                    try {
                        URL url = new URL(path);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        int code = conn.getResponseCode();
                        if (200 == code) {
                            InputStream is = conn.getInputStream();
                            // 内存中的图片
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            FileOutputStream fos = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            fos.close();
                            is.close();

                            Message msg = Message.obtain();
                            msg.what = LOAD_IMAGE;
                            msg.obj = bitmap;
                            handler.sendMessage(msg);
                            //iv.setImageBitmap(bitmap);
                        } else {
                            Message msg = Message.obtain();
                            msg.what = LOAD_ERR;
                            msg.obj = "获取图片失败,返回码:"+code;
                            handler.sendMessage(msg);
                            //Toast.makeText(MainActivity.this, "获取失败.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Message msg = Message.obtain();
                        msg.what = LOAD_ERR;
                        msg.obj = "获取图片失败";
                        handler.sendMessage(msg);
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    /**
     * 上一张图片
     * @param view
     */
    public void pre(View view) {
        currentPosition--;
        if(currentPosition<0){
            currentPosition = paths.size()-1;
        }
        loadImageByPath(paths.get(currentPosition));
    }

    /**
     * 下一张图片
     * @param view
     */
    public void next(View view) {
        currentPosition++;
        if(currentPosition>(paths.size()-1)){
            currentPosition = 0;
        }
        loadImageByPath(paths.get(currentPosition));
    }
}
