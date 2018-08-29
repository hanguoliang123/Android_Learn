package com.ecarx.qqlogin;

import android.icu.text.IDNA;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText mEt_qqnumber;
    private EditText mEt_passwd;
    private CheckBox mCb_remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mEt_qqnumber = (EditText) findViewById(R.id.et_qqNum);
        mEt_passwd = (EditText) findViewById(R.id.et_passwd);
        mCb_remember = (CheckBox) findViewById(R.id.cb_remember);

        restoreInfo();

    }

    /**
     * 根据原来保存的文件信息，把QQ号码和密码信息显示到界面
     */
    private void restoreInfo() {
        File file = new File(this.getFilesDir(),"info.txt");
        // 如果文件存在，并且有内容，就读取出来
        if(file.exists()&&file.length()>0){
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String info = br.readLine();
                String qq = info.split("##")[0];
                String passwd = info.split("##")[1];
                mEt_qqnumber.setText(qq);
                mEt_passwd.setText(passwd);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
                File file = new File(this.getFilesDir(), "info.txt");
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    String info = qq + "##" + passwd;
                    fos.write(info.getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
