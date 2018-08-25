package com.ecarx.learnlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout root;
    private Button btnClickme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        setContentView(root);

        for(int i=0;i<5;i++) {
            btnClickme = new Button(this);
//            btnClickme.setText("Click me");
            btnClickme.setText("Remove me");
            btnClickme.setOnClickListener(this);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.weight=1;
            root.addView(btnClickme,lp);
//            root.addView(btnClickme, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
//        root.addView(btnClickme);
//        root.addView(btnClickme,300,200);
//        root.addView(btnClickme,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        root.addView(btnClickme,lp);
    }

    @Override
    public void onClick(View v) {
        root.removeView(v);
    }
}
