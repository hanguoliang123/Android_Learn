package com.ecarx.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyDBContentProvider extends ContentProvider {

    /**
     * 定义一个uri路径的匹配器，检查uri是否合法，如果路径不合法匹配失败返回-1，相当于保安
     */
    private static UriMatcher mUriMacher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int CONTACT_INFO_ALL = 1;
    private static final int CONTACT_INFO_SINGLE = 1;

    static {
        mUriMacher.addURI("com.ecarx.db","contactinfo", CONTACT_INFO_ALL);
        mUriMacher.addURI("com.ecarx.db","contactinfo/#", CONTACT_INFO_SINGLE);
    }

    private MyDBOpenHelper helper;

    public MyDBContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int code = mUriMacher.match(uri);
        if(code == CONTACT_INFO_ALL){
            SQLiteDatabase db = helper.getWritableDatabase();
            int count = db.delete("contactinfo", selection, selectionArgs);
            db.close();
            return count;
        }else{
            //匹配失败
            throw new IllegalArgumentException("暗号对错了，拖出去打一顿");
        }
    }

    @Override
    public String getType(Uri uri) {
        int code = mUriMacher.match(uri);
        if(code == CONTACT_INFO_ALL){
            return "vnd.android.cursor.dir/contact";
        }else if(code == CONTACT_INFO_SINGLE){
            return "vnd.android.cursor.item/contact";
        }else{
            //匹配失败
            throw new IllegalArgumentException("暗号对错了，拖出去打一顿");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int code = mUriMacher.match(uri);
        if(code == CONTACT_INFO_ALL){
            SQLiteDatabase db = helper.getWritableDatabase();
            long row = db.insert("contactinfo", null, values);
            db.close();
            return Uri.parse("content://com.ecarx.db/contactinfo/3"+row);
        }else{
            //匹配失败
            throw new IllegalArgumentException("暗号对错了，拖出去打一顿");
        }
    }

    @Override
    public boolean onCreate() {
        helper = new MyDBOpenHelper(getContext());
        return true;
    }

    /**
     * 查询的方法
     * @param uri:查询的路径
     * @param projection:查询数据某个表，哪些列的内容
     * @param selection:选择条件
     * @param selectionArgs:选择条件的参数
     * @param sortOrder:排序方式
     * @return
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        /**
         * 判断请求是否符合
         */
        int code = mUriMacher.match(uri);
        if(code == CONTACT_INFO_ALL){
            // 得到一个可读的数据库
            SQLiteDatabase db = helper.getReadableDatabase();
            return db.query("contactinfo",projection,selection,selectionArgs,null,null,sortOrder);
        }else if(code == CONTACT_INFO_SINGLE){
            // content://com.ecarx.db/contactinfo/3
            String path = uri.toString();
            SQLiteDatabase db = helper.getReadableDatabase();
            return db.query("contactinfo",projection,"id=?",new String[]{path.substring(path.lastIndexOf("/")+1)},null,null,sortOrder);
        }else{
            //匹配失败
            throw new IllegalArgumentException("暗号对错了，拖出去打一顿");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int code = mUriMacher.match(uri);
        if(code == CONTACT_INFO_ALL){
            SQLiteDatabase db = helper.getWritableDatabase();
            int count = db.update("contactinfo", values,selection,selectionArgs);
            db.close();
            return count;
        }else{
            //匹配失败
            throw new IllegalArgumentException("暗号对错了，拖出去打一顿");
        }
    }
}
