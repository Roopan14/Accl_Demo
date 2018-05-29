package com.example.roopanc.accl_demo;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    ImageView imageView;

    SensorManager sensorManager;
    Sensor mAcclSensor;

    public static int x = 0;
    public static int y = 0;

    public int xmax, ymax;
    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAcclSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Display display = getWindowManager().getDefaultDisplay();
        xmax = display.getWidth()-50;
        ymax = display.getHeight()-50;
        Log.d("Xmax", ""+xmax);
        Log.d("Ymax", ""+ymax);
        imageView = findViewById(R.id.ballimg);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, mAcclSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //move ball based on accl sensor values

            Log.d("Xvalues", ""+event.values[0]);
            Log.d("Yvalues", ""+event.values[1]);

            x -= (int) event.values[0];
            y += (int) event.values[1];

            if (x>xmax)
            {
                x=xmax-50;
            }
            if (x<0)
            {
                x=0;
            }

            if (y>ymax)
            {
                y=ymax-250;
            }
            if (y<0)
            {
                y=0;
            }


            Log.d("xposition", "" + x);
            Log.d("yposition", "" + y);
            imageView.setY(y);
            imageView.setX(x);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
