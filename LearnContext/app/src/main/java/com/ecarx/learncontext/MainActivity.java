package com.ecarx.learncontext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

//    private TextView tv;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        System.out.println("MainActivity onCreate.");

//        tv = new TextView(MainActivity.this);
        //tv.setText("Hello Android.");
//        tv.setText(R.string.hello_world);
//        setContentView(tv);

/*        ImageView iv = new ImageView(this);
        iv.setImageResource(R.mipmap.ic_launcher);
        setContentView(iv);

        System.out.println(getResources().getText(R.string.hello_world))*/;

        setContentView(R.layout.main1);


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
