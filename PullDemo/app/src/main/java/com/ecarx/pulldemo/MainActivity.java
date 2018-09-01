package com.ecarx.pulldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view){
        try {
            InputStream is = this.getAssets().open("info.xml");
            // 解析info.xml文件
            // 1.得到xml文件的解析器
            XmlPullParser parser = Xml.newPullParser();
            // 2.设置输入流和编码
            parser.setInput(is,"utf-8");

            // 3.解析xml文件，获取当前的事件类型
            int eventType = parser.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT){
                System.out.println(parser.getEventType()+"----"+parser.getName()+"----"+parser.getText());
                eventType = parser.next();
            }

            System.out.println(parser.getEventType()+"----"+parser.getName()+"----"+parser.getText());
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
