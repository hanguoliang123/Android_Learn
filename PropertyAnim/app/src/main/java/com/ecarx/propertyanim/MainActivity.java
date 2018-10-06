package com.ecarx.propertyanim;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "您点击我", Toast.LENGTH_SHORT).show();
            }
        });

        //补间动画，控件还在原来的地方，只不过绘制的图像在变化
//        TranslateAnimation ta;
//        ta = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,-0.5f,Animation.RELATIVE_TO_PARENT,0.5f,
//                Animation.RELATIVE_TO_PARENT,-0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
//        ta.setDuration(4000);
//        ta.setRepeatCount(Animation.INFINITE);  //不停止，一直播放
//        ta.setRepeatMode(Animation.REVERSE);
//
//        iv.startAnimation(ta);

        // 属性动画，控件会随着动画改变
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "translationX", 0, 10.0f, 20.0f, 30.0f, 40.0f, 100.0f, 200.0f,800.0f);
        oa.setDuration(5000);
        oa.setRepeatCount(ObjectAnimator.INFINITE);
        oa.setRepeatMode(ObjectAnimator.RESTART);
        oa.start();

    }
}
