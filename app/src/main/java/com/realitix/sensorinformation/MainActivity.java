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
    private Sensor mGravity, mAccelometer, mLight;
    private float gravityValue, accelometerValue, lightValue;
    private TextView txtMGravity, txtMAccelometer, txtMLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensors.forEach((sensor) -> System.out.println(sensor.getName()));
        mGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mAccelometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        setContentView(R.layout.activity_main);
        txtMGravity = (TextView) findViewById(R.id.gravity);
        txtMAccelometer = (TextView) findViewById(R.id.accelometer);
        txtMLight = (TextView) findViewById(R.id.light);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float value = sensorEvent.values[0];
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_GRAVITY:
                gravityValue = value;
                txtMGravity.setText(String.valueOf(gravityValue));
                break;
            case Sensor.TYPE_ACCELEROMETER:
                accelometerValue =value;
                txtMAccelometer.setText(String.valueOf(accelometerValue));
                break;
            case Sensor.TYPE_LIGHT:
                lightValue = value;
                txtMLight.setText(String.valueOf(lightValue));
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
        sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
