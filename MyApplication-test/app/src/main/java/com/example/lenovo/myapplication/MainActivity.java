package com.example.lenovo.myapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.net.Uri;
import android.provider.MediaStore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.my_layout);

        findViewById(R.id.btnStartAnotherAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this,AnotherAty.class));
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.baidu.com/")));
            }
        });


/*
        Button btnSave = (Button) findViewById(R.id.main_button);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "test1");
                contentValues.put("tel", "123456");
                Uri uri = Uri.parse("content://myprovider/userinfo");
                ContentResolver contentResolver = getContentResolver();
                contentResolver.insert(uri, contentValues);
            }
        });

        Button btnQuery = (Button) findViewById(R.id.main_button1);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://myprovider/userinfo");
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(uri, null, null, null, null);
                if (cursor.moveToFirst()) {
                    Log.e("btnQuery", "onClick:name=========" + cursor.getString(cursor.getColumnIndex("name")));
                    Log.e("btnQuery", "onClick:tel=========" + cursor.getString(cursor.getColumnIndex("tel")));
                }
            }
        });

        Button btnOpenWifi = (Button)findViewById(R.id.main_button2);
        btnOpenWifi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //获取ContentResolver实例
                ContentResolver contentResolver = getContentResolver();
                //设置查询条件
                String selection = MediaStore.Images.Media.DATA + " like ?";
                //设置查询条件参数，这里指定了要查询的目录
                String path = "%/mnt/sdcard/";
                String[] selectionArgs = {path + "%"};
                //使用指定的查询条件查询图片，如果不指定则selection和selectionArgs都为null.
                //如果没有指定，将会查询外存储中"/mnt/sdcard“中所有课查询的图片
                Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,
                        selection,selectionArgs,null);
                String[] columnNames = cursor.getColumnNames();
                //使用cursor.moveToNext()判断查询的结果集 中是否有下一条数据
                //如果有继续循环，否则中止循环
                while(cursor.moveToNext()){
                    //循环遍历所查询表的所有字段
                    //使用cursor.getColumnIndex()根据字段名取得对应的下表index
                    //再使用cursor.getXXX方法根据下标取得对应的数据
                    //其中getXXX()的XXX表示数据类型,如getString(),getInt()等
                    for(int i = 0;i<columnNames.length;i++){
                        String columnName = columnNames[i];
                        String string = cursor.getString(cursor.getColumnIndex(columnName));
                        Log.e("MainActivity","onClick"+columnName+"========"+string);
                    }
                }
            }

        });
*/
    }
}
