package com.ecarx.readcontacts.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.ecarx.readcontacts.domain.ContactInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @version $Rev$
 * @authod:Guoliang.Han
 * @des ${TODO}
 * @updateAuthor $authod$
 * @updateDes ${TODO}
 */
public class ContactInfoUtils {
    /**
     * 获取系统里面的全部的联系人信息
     *
     * @param context 上下文
     * @return 联系人信息的集合
     */
    public static List<ContactInfo> getAllContactInfos(Context context) {
        // 1.查询 raw_contacts表，把每个联系人的contact_id读取出来
        ContentResolver resolver = context.getContentResolver();

        // 2.将uri字符串转换为uri对象
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cursor = resolver.query(uri, new String[]{"contact_id"}, null, null, null);
        // 创建一个集合，存放所有联系人信息
        ArrayList<ContactInfo> contactinfos = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            System.out.println("联系人id:" + id);
            if (id != null) {
                ContactInfo contactInfo = new ContactInfo();
                Uri dataUri = Uri.parse("content://com.android.contacts/data");
                // 3.根据contact_id查询data表，把联系人的数据提取出来
                Cursor dataCursor = resolver.query(dataUri, new String[]{"data1", "mimetype"}, "raw_contact_id=?", new String[]{id}, null);
                // 4.根据查询出来的mimetype再查询mimetype表，解析联系人信息类型
                while (dataCursor.moveToNext()) {
                    String data1 = dataCursor.getString(0);
                    String mimetype = dataCursor.getString(1);
                    if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
                        contactInfo.setPhone(data1);

                    } else if ("vnd.android.cursor.item/email_v2".equals(mimetype)) {
                        contactInfo.setEmail(data1);
                    } else if ("vnd.android.cursor.item/name".equals(mimetype)) {
                        contactInfo.setName(data1);
                    } else if ("vnd.android.cursor.item/im".equals(mimetype)) {
                        contactInfo.setQq(data1);
                    }
                }
                contactinfos.add(contactInfo);
                dataCursor.close();
            }
        }
        cursor.close();
        return contactinfos;
    }
}
