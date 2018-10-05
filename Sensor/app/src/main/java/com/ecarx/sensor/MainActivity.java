package com.ecarx.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private MyListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取传感器的服务
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        // 得到光线传感器
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        mListener = new MyListener();

        mSensorManager.registerListener(mListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    private class MyListener implements SensorEventListener{

        /**
         * 当精度变化的时候调用的方法
         * @param event
         */
        @Override
        public void onSensorChanged(SensorEvent event) {
            float light = event.values[0];
            System.out.println("当前光线强度："+light);
        }

        /**
         * 当传感器发现数据变化的时候调用的方法
         * @param sensor
         * @param accuracy
         */
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    @Override
    protected void onDestroy() {
        mSensorManager.unregisterListener(mListener);
        super.onDestroy();
    }
}
