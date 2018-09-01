package com.ecarx.createdb.dao;

import android.content.ContentValues;
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
     * @return：返回的是添加在数据库的行号，-1：失败
     */
    public long add(String name, String phone) {
        SQLiteDatabase db = helper.getWritableDatabase();
        //        db.execSQL("insert into contactinfo (name,phone) values(?,?);", new Object[]{name, phone});
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        long rawid = db.insert("contactinfo", null, values);

        // 记得关闭数据库，释放资源
        db.close();
        return rawid;
    }

    /**
     * 根据姓名删除一条记录
     *
     * @param name：联系人姓名
     * @return：0：没有删除任何记录，返回整形int代表删除了几条数据
     */
    public int delete(String name) {
        SQLiteDatabase db = helper.getWritableDatabase();
        // db.execSQL("delete from contactinfo where name=?;", new Object[]{name});
        int rawcount = db.delete("contactinfo", "name=?", new String[]{name});
        // 记得关闭数据库，释放资源
        db.close();
        return rawcount;
    }

    /**
     * 修改联系人电话号码
     *
     * @param name：联系人姓名
     * @param phone：联系人电话
     * @return：0：没有修改任何记录，返回整形int代表修改了几条数据
     */
    public int update(String name, String phone) {
        SQLiteDatabase db = helper.getWritableDatabase();
        // db.execSQL("update contactinfo set phone=? where name=?;", new Object[]{phone, name});

        ContentValues values = new ContentValues();
        values.put("phone",phone);
        int rawcount = db.update("contactinfo", values, "name=?", new String[]{name});
        // 记得关闭数据库，释放资源
        db.close();
        return rawcount;
    }

    /**
     * 查询联系人的电话号码
     *
     * @param name：联系人姓名
     * @return :返回电话号码
     */
    public String query(String name) {
        SQLiteDatabase db = helper.getReadableDatabase();
        // Cursor cursor = db.rawQuery("select phone from contactinfo where name=?", new String[]{name});

        Cursor cursor = db.query("contactinfo", new String[]{"phone"}, "name=?", new String[]{name}, null, null, null);

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
