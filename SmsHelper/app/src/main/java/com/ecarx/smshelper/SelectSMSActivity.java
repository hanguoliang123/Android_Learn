package com.ecarx.smshelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectSMSActivity extends AppCompatActivity {

    private ListView lv;
    private String[] smscontents = {"sdssgl","sdssglhello","sdss","wa'o","yaha","ooo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sms);

        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<String>(this,R.layout.item_textview,smscontents));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent data = new Intent();
                String text = smscontents[position];
                data.putExtra("text", text);
                setResult(0,data);
                finish();
            }
        });
    }
}
