package com.ecarx.otherapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 插入数据
     * @param view
     */
    public void insert(View view){
        //1.获取内容提供者的解析器
        ContentResolver resolver = getContentResolver();

        //2.将字符串转换为uri
        Uri uri = Uri.parse("content://com.ecarx.db/contactinfo/3");
        ContentValues values = new ContentValues();
        values.put("name","李四");
        values.put("phone","9999999");
        Uri result = resolver.insert(uri, values);
        Toast.makeText(this,result.toString(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 删除数据
     * @param view
     */
    public void delete(View view){
        //1.获取内容提供者的解析器
        ContentResolver resolver = getContentResolver();

        //2.将字符串转换为uri
        Uri uri = Uri.parse("content://com.ecarx.db/contactinfo/3");

        int count = resolver.delete(uri, "name=?",new String[]{"李四"});
        Toast.makeText(this,"删除了"+count +"条记录", Toast.LENGTH_SHORT).show();
    }

    /**
     * 修改数据
     * @param view
     */
    public void update(View view){
        //1.获取内容提供者的解析器
        ContentResolver resolver = getContentResolver();

        //2.将字符串转换为uri
        Uri uri = Uri.parse("content://com.ecarx.db/contactinfo/3");
        ContentValues values = new ContentValues();
        values.put("phone","666666");
        int count = resolver.update(uri, values,"name=?",new String[]{"李四"});
        Toast.makeText(this,"修改了"+count +"条记录", Toast.LENGTH_SHORT).show();
    }

    /**
     * 查询数据
     * @param view
     */
    public void query(View view){
        //1.获取内容提供者的解析器
        ContentResolver resolver = getContentResolver();

        //2.将字符串转换为uri
        Uri uri = Uri.parse("content://com.ecarx.db/contactinfo/3");
        String type = resolver.getType(uri);
        //vnd.android.cursor.item/contact,只有一条记录
        //  vnd.android.cursor.dir/contact 多条记录
        if(type.startsWith("vnd.android.cursor.dir/contact")){
            System.out.println("vnd.android.cursor.dir/contact");
            Cursor cursor = resolver.query(uri, null, null, null, null);
            while(cursor.moveToNext()){
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                System.out.println("id: "+id+"-name:"+name+"-phone:"+phone);
            }
            cursor.close();
        }else if(type.startsWith("vnd.android.cursor.item/contact")){
            System.out.println("一条记录");
            Cursor cursor = resolver.query(uri, null, null, null, null);
            cursor.moveToFirst();
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            System.out.println("id: "+id+"-name:"+name+"-phone:"+phone);
            cursor.close();
        }

    }

}
