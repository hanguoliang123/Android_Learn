package com.ecarx.learnintent;

import android.app.Activity;
import android.os.Bundle;
;

/**
 * Created by Lenovo on 2018/8/21.
 */
public class MyAty extends Activity{

    public static final String ACTION = "com.ecarx.learnintent.intent.action.MyAty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaty);
    }
}
