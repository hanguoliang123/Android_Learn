package com.ecarx.observer;

import android.Manifest;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        performCodeWithPermission("申请READ_SMS权限", new PermissionCallback() {
            @Override
            public void hasPermission() {

            }

            @Override
            public void noPermission() {

            }
        }, Manifest.permission.READ_SMS);
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        resolver.registerContentObserver(uri,true,new MyObserver(new Handler()));
    }

    private class MyObserver extends ContentObserver {
        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            System.out.println("监视到数据库的内容变化了");
            Uri uri = Uri.parse("content://sms/");
            Cursor cursor = getContentResolver().query(uri, new String[]{"address", "body", "date", "type"}, null, null, "date desc");
            cursor.moveToFirst();
            String address = cursor.getString(0);
            String body = cursor.getString(1);
            System.out.println("address:"+address);
            System.out.println("body:"+body);

            super.onChange(selfChange);
        }
    }
}
