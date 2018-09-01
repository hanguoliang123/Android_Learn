package com.ecarx.createdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ecarx.createdb.dao.ContactInfoDao;

public class MainActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mphone;
    private ContactInfoDao mDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.找到需要用到的控件
        mName = (EditText) findViewById(R.id.et_name);
        mphone = (EditText) findViewById(R.id.et_phone);
        // 2.new一个dao出来
        mDao = new ContactInfoDao(this);
    }

    /**
     * 添加一条联系人信息
     * @param view
     */
    public void add(View view){
        String name = mName.getText().toString().trim();
        String phone = mphone.getText().toString().trim();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)){
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else{
            mDao.add(name,phone);
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 删除一条联系人信息
     * @param view
     */
    public void delete(View view){
        String name = mName.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else{
            mDao.delete(name);
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 修改联系人号码
     * @param view
     */
    public void update(View view){
        String name = mName.getText().toString().trim();
        String phone = mphone.getText().toString().trim();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)){
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else{
            mDao.update(name,phone);
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 查询一条联系人信息
     * @param view
     */
    public void query(View view){
        String name = mName.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else{
            String query_name;
            query_name = mDao.query(name);
            Toast.makeText(this,"查询结果为"+query_name, Toast.LENGTH_SHORT).show();
        }
    }
}
