package com.ecarx.qqlogin2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText mEt_qqnumber;
    private EditText mEt_passwd;
    private CheckBox mCb_remember;

    /**
     * 1.定义一个共享参数(存放数据方便的api)
     */
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /**
         * 2.通过上下文得到一个共享参数的实例对象
         */
        sp = this.getSharedPreferences("config", this.MODE_PRIVATE);

        mEt_qqnumber = (EditText) findViewById(R.id.et_qqNum);
        mEt_passwd = (EditText) findViewById(R.id.et_passwd);
        mCb_remember = (CheckBox) findViewById(R.id.cb_remember);

        restoreInfo();

    }

    /**
     * 从sp文件当中键值信息
     */
    private void restoreInfo() {
        String qq = sp.getString("qq", "");
        String passwd = sp.getString("passwd", "");

        mEt_qqnumber.setText(qq);
        mEt_qqnumber.setText(passwd);
    }

    /**
     * 登陆按钮的点击事件
     * @param v
     */
    public void login(View view){
        String qq = mEt_qqnumber.getText().toString().trim();
        String passwd = mEt_passwd.getText().toString().trim();

        if(TextUtils.isEmpty(qq)||TextUtils.isEmpty(passwd)){
            Toast.makeText(this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }else{
            // 判断是否需要记住用户名和密码
            if(mCb_remember.isChecked()){
                //被选中状态需要记住用户名和密码
                // 将数据保存到sp中
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("qq",qq);
                editor.putString("passwd",passwd);
                editor.commit();    //提交数据，类似关闭流事务
            }

            //登陆操作，模拟登陆，数据应该提交给服务器比较是否正确
            if("10000".equals(qq)&&"123456".equals(passwd)){
                Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"登陆失败",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
