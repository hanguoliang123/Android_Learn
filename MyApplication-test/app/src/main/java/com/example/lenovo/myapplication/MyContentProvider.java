package com.example.lenovo.myapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Lenovo on 2018/8/19.
 */
public class MyContentProvider extends ContentProvider{
    private MyDataBaseHelper myDataBaseHelper;

    public MyContentProvider(){

    }
    @Override
    public boolean onCreate(){
        myDataBaseHelper = new MyDataBaseHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri){
        //TODO:Implement this to handle requests for te MIME type of the data
        // at hte given URI.
        throw new UnsupportedOperationException("Not yet implemented.");
    }
    @Override
    public Uri insert(Uri uri,ContentValues values){
        SQLiteDatabase db = myDataBaseHelper.getWritableDatabase();
        db.insert("userinfo",null,values);
        return uri;
    }
    @Override
    public Cursor query(Uri uri,String[] projection,String selection,
                 String[] selectionArgs,String sortOrder){
        SQLiteDatabase db = myDataBaseHelper.getWritableDatabase();
        Cursor cursor = db.query("userinfo",projection,selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }
    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs){
        throw new UnsupportedOperationException("Not yet implemented.");
    }
    @Override
    public int update(Uri uri, ContentValues values,String selection, String[] selectionArgs){
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
