package com.ecarx.tweenanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

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
        AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
        aa.setDuration(2000);
        aa.setRepeatCount(2);
        aa.setRepeatMode(Animation.REVERSE);

        iv.startAnimation(aa);
    }

    /**
     * 缩放的动画
     * @param view
     */
    public void scale(View view){
        ScaleAnimation sa;
        sa = new ScaleAnimation(0.0f, 2.0f,0.0f,2.0f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(2000);
        sa.setRepeatCount(2);
        sa.setRepeatMode(Animation.REVERSE);

        iv.startAnimation(sa);
    }

    /**
     * 位移的动画
     * @param view
     */
    public void trans(View view){
        TranslateAnimation ta;
        ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF,-0.5f,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,-0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ta.setDuration(2000);
        ta.setRepeatCount(2);
        ta.setRepeatMode(Animation.REVERSE);

        iv.startAnimation(ta);
    }

    /**
     * 旋转的动画
     * @param view
     */
    public void rotate(View view){
        RotateAnimation ra;
        ra = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(2000);
        ra.setRepeatCount(2);
        ra.setRepeatMode(Animation.REVERSE);

        iv.startAnimation(ra);
    }

    /**
     * 集合的动画
     * @param view
     */
    public void set(View view){
        AnimationSet set;
        set = new AnimationSet(false);

        TranslateAnimation ta;
        ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF,-0.5f,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,-0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ta.setDuration(2000);
        ta.setRepeatCount(2);
        ta.setRepeatMode(Animation.REVERSE);

        RotateAnimation ra;
        ra = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(2000);
        ra.setRepeatCount(2);
        ra.setRepeatMode(Animation.REVERSE);


        ScaleAnimation sa;
        sa = new ScaleAnimation(0.0f, 2.0f,0.0f,2.0f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(2000);
        sa.setRepeatCount(2);
        sa.setRepeatMode(Animation.REVERSE);

        set.addAnimation(sa);
        set.addAnimation(ta);
        set.addAnimation(ra);
        iv.startAnimation(set);

    }
}
