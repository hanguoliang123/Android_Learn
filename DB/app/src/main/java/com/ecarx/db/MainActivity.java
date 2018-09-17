package com.ecarx.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

    public void click(View view){
        MyDBOpenHelper helper = new MyDBOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        long basenumber = 1350;
        for(int i=0;i<100;i++){
            ContentValues values = new ContentValues();
            values.put("name","张三"+i);
            values.put("phone",String.valueOf(basenumber+i));
            db.insert("contactinfo",null,values);

        }
        db.close();
        Toast.makeText(this, "添加模拟数据成功", Toast.LENGTH_SHORT).show();
    }

}
