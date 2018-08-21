package com.ecarx.sendargs;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView2);

        findViewById(R.id.btnStartAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,TheAty.class);
                //i.putExtra("data","Hello sdssgl.");
                //传递数据包
//                Bundle b = new Bundle();
//                b.putString("name","sdssgl---bundle.");
//                b.putInt("age",2);
//                b.putString("name1","sdssgl-name1.");
//
//                i.putExtra("data",b);
                //i.putExtras(b);

                i.putExtra("user",new User("sdssgl",25));

                //startActivity(i);
                startActivityForResult(i,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        textView.setText("另一个Activity返回的数据是: "+data.getStringExtra("data"));
    }

}
