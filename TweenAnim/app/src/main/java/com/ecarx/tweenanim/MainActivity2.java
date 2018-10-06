package com.ecarx.tweenanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.imageView);
    }

    /**
     * 透明度变化的动画
     * @param view
     */
    public void alpha(View view){
        Animation aa = AnimationUtils.loadAnimation(this, R.anim.alpha);
        iv.startAnimation(aa);
    }

    /**
     * 缩放的动画
     * @param view
     */
    public void scale(View view){
        Animation aa = AnimationUtils.loadAnimation(this, R.anim.scale);
        iv.startAnimation(aa);
    }

    /**
     * 位移的动画
     * @param view
     */
    public void trans(View view){
        Animation aa = AnimationUtils.loadAnimation(this, R.anim.trans);
        iv.startAnimation(aa);
    }

    /**
     * 旋转的动画
     * @param view
     */
    public void rotate(View view){
        Animation aa = AnimationUtils.loadAnimation(this, R.anim.rotate);
        iv.startAnimation(aa);
    }

    /**
     * 集合的动画
     * @param view
     */
    public void set(View view){
        Animation aa = AnimationUtils.loadAnimation(this, R.anim.set);
        iv.startAnimation(aa);
    }
}
