package com.ecarx.savexmlinfo;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

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
        final String name = mEt_name.getText().toString().trim();
        final String age = mEt_age.getText().toString().trim();
        final String id = mEt_id.getText().toString().trim();
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
                    // 1.创建一个xml文件的序列化器
                    XmlSerializer serializer = Xml.newSerializer();
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory(),"info.xml"));

                        // 2.设置文件的输出和编码方式
                        serializer.setOutput(fos,"utf-8");
                        // 3.写xml文件的头
                        serializer.startDocument("utf-8",true);

                        // 4.写info节点
                        serializer.startTag(null,"info");

                        // 5.写student节点
                        serializer.startTag(null,"student");

                        // 6.写属性
                        serializer.attribute(null,"id",id);

                        // 7.写name
                        serializer.startTag(null,"name");
                        serializer.text(name);
                        serializer.endTag(null,"name");
                        // 8.写age
                        serializer.startTag(null,"age");
                        serializer.text(age);
                        serializer.endTag(null,"age");

                        serializer.endTag(null,"student");
                        serializer.endTag(null,"info");
                        serializer.endDocument();
                        fos.close();
                        Toast.makeText(MainActivity.this, "保存学生信息成功", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
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
