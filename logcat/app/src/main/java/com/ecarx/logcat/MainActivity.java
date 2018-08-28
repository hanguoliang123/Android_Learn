package com.ecarx.logcat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG,"我是v级别的log打印");
        Log.d(TAG,"我是d级别的log打印");
        Log.i(TAG,"我是i级别的log打印");
        Log.w(TAG,"我是w级别的log打印");
        Log.e(TAG,"我是e级别的log打印");
    }
}
