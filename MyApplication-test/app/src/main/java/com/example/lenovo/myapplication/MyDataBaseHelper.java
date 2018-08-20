package com.example.lenovo.myapplication;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Lenovo on 2018/8/19.
 */
public class MyDataBaseHelper extends SQLiteOpenHelper{
    public MyDataBaseHelper(Context context){
        super(context,"mydb",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE userinfo (name VARCHAR(20),tel VARCHAR(20))";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
