package com.ecarx.learncontext;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Lenovo on 2018/8/22.
 */
public class Main2 extends Activity {

    private TextView textView;
    private EditText editText;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("Main2 onCreate.");

        setContentView(R.layout.main2);


        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);

        textView.setText("共享的数据是:"+getApp().getTextData());

        findViewById(R.id.btnSaveData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((App)getApplication()).setTextData(editText.getText().toString());

                textView.setText("共享的数据是:"+getApp().getTextData());
            }
        });
    }
    public App getApp(){
        return (App)getApplicationContext();
    }
}
