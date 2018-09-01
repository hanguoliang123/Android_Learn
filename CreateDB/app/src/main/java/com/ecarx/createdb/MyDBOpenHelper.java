package com.ecarx.createdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @version $Rev$
 * @authod:Guoliang.Han
 * @des ${TODO}
 * @updateAuthor $authod$
 * @updateDes ${TODO}
 */
public class MyDBOpenHelper extends SQLiteOpenHelper {
    /**
     * 第一个参数是上下文
     * 第二个参数是数据库名称
     * 第三个参数null表示使用默认的游标工厂
     * 第四个参数是数据库的版本号，数据库只能升级不能降级，版本号只能变大，不能变小
     * @param context
     */
    public MyDBOpenHelper(Context context) {
        super(context, "ecarxAndroid", null, 2);
    }

    /**
     * Called when the database is created for the first time.
     * 当数据库第一次被创建时被调用的方法，适合在这个方法里面把数据的表结构定义出来
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate:数据库被创建了");
        db.execSQL("create table contactinfo(id integer primary key autoincrement,name char(20),phone varchar(20))");

    }

    /**
     * Called when the database needs to be upgrade.
     * 当数据库更新的时候调用的方法
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade数据库被更新了");
        db.execSQL("alter table contactinfo add account varchar(20)");
    }
}
