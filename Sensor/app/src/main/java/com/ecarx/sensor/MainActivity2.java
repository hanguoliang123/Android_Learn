package com.ecarx.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private SensorManager mSensorManager;
    private MyListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mListener = new MyListener();
        mSensorManager.registerListener(mListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    private class MyListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            float angle = event.values[0];
            System.out.println("angle:"+angle);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }


    @Override
    protected void onDestroy() {
        mSensorManager.unregisterListener(mListener);
        mListener = null;
        super.onDestroy();
    }
}
