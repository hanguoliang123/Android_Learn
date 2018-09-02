package com.ecarx.htmlviewer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ecarx.htmlviewer.utils.StreamUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final int LOAD_SUCCESS = 1;
    private static final int LOAD_ERR     = 2;
    private EditText et_url;
    private TextView tv_result;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LOAD_SUCCESS:
                    String text = (String) msg.obj;
                    tv_result.setText(text);
                    break;
                case LOAD_ERR:
                    Toast.makeText(MainActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_url = (EditText) findViewById(R.id.et_url);
        tv_result = (TextView) findViewById(R.id.tv_result);
    }

    /**
     * 查看网页源码,访问网络的操作:1.声明权限. 2.开启子线程
     * @param view
     */
    public void viewHTMLSource(View view){
        final String str_url = et_url.getText().toString().trim();
        if(TextUtils.isEmpty(str_url)){
            Toast.makeText(this, "url不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(str_url);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    int code = conn.getResponseCode();
                    if(200==code){
                        InputStream is = conn.getInputStream();
                        String text = StreamUtils.readStream(is);

                        Message msg = Message.obtain();
                        msg.what = LOAD_SUCCESS;
                        msg.obj = text;
                        mHandler.sendMessage(msg);
                    }else{
                        Message msg = Message.obtain();
                        msg.what = LOAD_ERR;
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = LOAD_ERR;
                    mHandler.sendMessage(msg);
                }

            }
        }.start();
    }
}
