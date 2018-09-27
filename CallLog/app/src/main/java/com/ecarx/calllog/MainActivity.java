package com.ecarx.calllog;

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
        performCodeWithPermission("读写联系人", new PermissionCallback() {
            @Override
            public void hasPermission() {

            }

            @Override
            public void noPermission() {

            }
        }, Manifest.permission.READ_CALL_LOG,Manifest.permission.WRITE_CALL_LOG,Manifest.permission.WRITE_SETTINGS);
    }

    public void readCallLog(View view){
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://call_log/calls");
        Cursor cursor = resolver.query(uri, new String[]{"number", "date", "type"}, null, null, null);
        while(cursor.moveToNext()){
            String number = cursor.getString(0);
            String date = cursor.getString(1);
            String type = cursor.getString(2);
            System.out.println("number: "+ number+"-----date:"+date+"-----type: "+type);
        }
        cursor.close();
    }

    public void insertCallLog(View view){
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://call_log/calls");
        ContentValues values = new ContentValues();
        values.put("number","123456789");
        values.put("date",System.currentTimeMillis());
        values.put("type","3");
        values.put("duration",3600);

        resolver.insert(uri,values);

    }
}
