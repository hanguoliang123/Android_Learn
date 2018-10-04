package com.ecarx.smsprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        performCodeWithPermission("读取短信", new PermissionCallback() {
            @Override
            public void hasPermission() {

            }

            @Override
            public void noPermission() {

            }
        }, Manifest.permission.READ_SMS);
    }

    public void readSms(View view){
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
        while(cursor.moveToNext()){
            String address = cursor.getString(0);
            String date = cursor.getString(1);
            String type = cursor.getString(2);
            String body = cursor.getString(3);
            System.out.println("address:"+address+"--date:"+date+"--type:"+type+"--body:"+body);
        }
        cursor.close();
    }

    public void insertSms(View view){
        new Thread(){
            @Override
            public void run() {
                ContentResolver resolver = getContentResolver();
                Uri uri = Uri.parse("content://sms/");
                ContentValues values = new ContentValues();
                values.put("address","94561");
                values.put("date", System.currentTimeMillis());
                values.put("type",1);
                values.put("body","yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
                resolver.insert(uri,values);
            }
        }.start();

    }

}
