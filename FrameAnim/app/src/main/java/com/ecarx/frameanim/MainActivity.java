package com.ecarx.frameanim;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setBackgroundResource(R.drawable.girl_anim);
        AnimationDrawable anim = (AnimationDrawable) iv.getBackground();
        anim.start();
    }
}
