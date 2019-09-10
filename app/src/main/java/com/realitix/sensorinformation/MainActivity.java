package com.realitix.sensorinformation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mGravity, mAccelometer;
    private float gravityValue, accelometerValue;
    private TextView txtMGravity;
    private TextView txtMAccelometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensors.forEach((sensor) -> System.out.println(sensor.getName()));
        mGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mAccelometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        setContentView(R.layout.activity_main);
        txtMGravity = (TextView) findViewById(R.id.gravity);
        txtMAccelometer = (TextView) findViewById(R.id.accelometer);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_GRAVITY:
                gravityValue = sensorEvent.values[0];
                txtMGravity.setText(String.valueOf(gravityValue));
                break;
            case Sensor.TYPE_ACCELEROMETER:
                accelometerValue = sensorEvent.values[0];
                txtMAccelometer.setText(String.valueOf(accelometerValue));
                break;
            default:
                gravityValue = 0.0f;
                txtMGravity.setText(R.string.nan);
                txtMAccelometer.setText(R.string.nan);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mAccelometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
