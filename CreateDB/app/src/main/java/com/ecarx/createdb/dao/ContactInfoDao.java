package com.ecarx.createdb.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ecarx.createdb.MyDBOpenHelper;

/**
 * @version $Rev$
 * @authod:Guoliang.Han
 * @des ${TODO}
 * @updateAuthor $authod$
 * @updateDes ${TODO}
 */
public class ContactInfoDao {

    private final MyDBOpenHelper helper;

    /**
     *
     */
    public ContactInfoDao(Context context) {
        helper = new MyDBOpenHelper(context);
    }

    /**
     * 添加一条记录
     *
     * @param name：联系人姓名
     * @param phone：联系人电话
     */
    public void add(String name, String phone) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into contactinfo (name,phone) values(?,?);", new Object[]{name, phone});
        // 记得关闭数据库，释放资源
        db.close();
    }

    /**
     * 删除一条记录
     *
     * @param name：联系人姓名
     */
    public void delete(String name) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from contactinfo where name=?;", new Object[]{name});
        // 记得关闭数据库，释放资源
        db.close();
    }

    /**
     * 修改联系人电话号码
     *
     * @param name：联系人姓名
     * @param phone：联系人电话
     */
    public void update(String name, String phone) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("update contactinfo set phone=? where name=?;", new Object[]{phone, name});
        // 记得关闭数据库，释放资源
        db.close();
    }

    /**
     * 查询联系人的电话号码
     *
     * @param name：联系人姓名
     */
    public String query(String name) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select phone from contactinfo where name=?", new String[]{name});
        String phone = null;
        if (cursor.moveToNext()) {
            phone = cursor.getString(0);
        }
        // 记得关闭数据库，释放资源
        cursor.close();
        db.close();
        return phone;
    }


}
