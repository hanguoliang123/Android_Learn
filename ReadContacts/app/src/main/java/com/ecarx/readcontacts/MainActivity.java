package com.ecarx.readcontacts;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.ecarx.readcontacts.domain.ContactInfo;
import com.ecarx.readcontacts.utils.ContactInfoUtils;

import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        performCodeWithPermission("读取联系人信息", new PermissionCallback() {
            @Override
            public void hasPermission() {

            }

            @Override
            public void noPermission() {

            }
        }, Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS);

    }

    public void click(View view){
        List<ContactInfo> infos =  ContactInfoUtils.getAllContactInfos(this);
        for(ContactInfo info:infos){
            System.out.println(info.toString());
        }
    }
}
