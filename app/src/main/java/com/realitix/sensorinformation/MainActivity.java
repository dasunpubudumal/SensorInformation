package com.realitix.sensorinformation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mGravity;
    private float gravityValue;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
//        sensors.forEach((sensor) -> System.out.println(sensor.getName()));
        mGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.gravity);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        gravityValue = sensorEvent.values[0];
        textView.setText(String.valueOf(gravityValue));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
