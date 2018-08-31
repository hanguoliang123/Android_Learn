package com.ecarx.savexmlinfo;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends BaseActivity {

    private EditText mEt_name;
    private EditText mEt_age;
    private EditText mEt_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.找到所有需要用到的控件
        mEt_name = (EditText) findViewById(R.id.et_name);
        mEt_age = (EditText) findViewById(R.id.et_age);
        mEt_id = (EditText) findViewById(R.id.et_id);

        // 2.将控件内容写入到xml文件当中
    }

    /**
     * 将控件内容写入到xml文件当中
     * @param view
     */
    public void save(View view){
        /* 3.根据控件读取用户输入的文本，放入到字符串变量中 */
        String name = mEt_name.getText().toString().trim();
        String age = mEt_age.getText().toString().trim();
        String id = mEt_id.getText().toString().trim();
        // 4.判断是否有空的内容
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(age)||TextUtils.isEmpty(id)){
            Toast.makeText(this, "信息不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else{
            // 5.将字符串写入到xml文件中
            final StringBuffer sb = new StringBuffer();
            sb.append("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>");
            sb.append("<info>");
            sb.append("<student id="+id+">");
            sb.append("<name>"+name+"</name>");
            sb.append("<age>"+age+"</age>");
            sb.append("</student>");
            sb.append("</info>");


            performCodeWithPermission("保存学生信息到sd卡", new PermissionCallback() {
                @Override
                public void hasPermission() {

                    File file = new File(Environment.getExternalStorageDirectory(), "info.xml");
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(sb.toString().getBytes());
                        fos.close();
                        Toast.makeText(MainActivity.this, "保存学生信息成功!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "保存学生信息失败!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void noPermission() {
                    Toast.makeText(MainActivity.this, "没有被授予权限", Toast.LENGTH_SHORT).show();
                }
            }, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }

    }
}
