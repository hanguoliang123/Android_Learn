package com.ecarx.weatherreport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.");
        getWeatherInfo();
        System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII.");
    }

    /**
     * 获取天气信息
     */
    private void getWeatherInfo() {
        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL222.");
        try {
            XmlPullParser parser = Xml.newPullParser();
            InputStream is = getAssets().open("getWeatherbyCityName.xml");
            System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL333.");
            parser.setInput(is,"utf-8");
            ArrayList<String> infos = new ArrayList<>();
            int type = parser.getEventType();
            while(type!=XmlPullParser.END_DOCUMENT){
                if("string".equals(parser.getName())){
                    String info = parser.nextText();
                    infos.add(info);
                }
                type = parser.next();
            }
            is.close();

            System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL4444.");
            String cityName = infos.get(0);
            String temp = infos.get(1);
            String weather = infos.get(2);
            String wind = infos.get(3);
            String wearInfo = infos.get(4);
            System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL555.");
            TextView tv = (TextView) findViewById(R.id.tv_info);
            tv.setText("城市名称："+cityName+"\n温度："+temp+"\n天气："+weather+"\n风力："+wind+"\n穿衣指数："+wearInfo);
            System.out.println("城市名称："+cityName+"\n温度："+temp+"\n天气："+weather+"\n风力："+wind+"\n穿衣指数："+wearInfo);
            Toast.makeText(this, "I'm Here.2", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
