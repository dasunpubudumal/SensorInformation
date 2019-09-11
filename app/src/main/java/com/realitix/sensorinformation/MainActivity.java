package com.realitix.sensorinformation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mGravity, mAccelometer, mLight, mHall, mProximity, mGyro;
    private TextView txtMGravity, txtMAccelometer, txtMLight, txtMHall, txtMProximity, txtMGyro;
    private Button btnStartStop;
    private boolean startSwitch = false;

    private void initializeSensors() {
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        mGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mAccelometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mHall = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMGravity = (TextView) findViewById(R.id.gravity);
        txtMAccelometer = (TextView) findViewById(R.id.accelometer);
        txtMLight = (TextView) findViewById(R.id.light);
        txtMHall = (TextView) findViewById(R.id.hall);
        txtMProximity = (TextView) findViewById(R.id.proximity);
        txtMGyro = (TextView) findViewById(R.id.gyro);
        btnStartStop = (Button) findViewById(R.id.startStop);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        initializeSensors();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float value = sensorEvent.values[0];
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_GRAVITY:
                txtMGravity.setText(String.valueOf(value));
                break;
            case Sensor.TYPE_ACCELEROMETER:
                txtMAccelometer.setText(String.valueOf(value));
                break;
            case Sensor.TYPE_LIGHT:
                txtMLight.setText(String.valueOf(value));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                txtMHall.setText(String.valueOf(value));
                break;
            case Sensor.TYPE_PROXIMITY:
                txtMProximity.setText(String.valueOf(value));
                break;
            case Sensor.TYPE_GYROSCOPE:
                txtMGyro.setText(String.valueOf(value));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    /**
     * Operates the Start button
     * @param view: current view
     */
    public void startPause(View view) {
        startSwitch = !startSwitch;
        if(startSwitch) {
            btnStartStop.setText(R.string.pause);
            sensorManager.registerListener(this, mGravity,
                    SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, mAccelometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, mLight,
                    SensorManager.SENSOR_DELAY_GAME);
            sensorManager.registerListener(this, mHall,
                    SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, mProximity,
                    SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, mGyro,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            btnStartStop.setText(R.string.start);
            sensorManager.unregisterListener(this);
        }
    }

    /**
     * Operates the Stop button
     * @param view: current view
     */
    public void stop(View view) {
        sensorManager.unregisterListener(this);
        txtMGravity.setText(String.valueOf(0));
        txtMAccelometer.setText(String.valueOf(0));
        txtMLight.setText(String.valueOf(0));
        txtMHall.setText(String.valueOf(0));
        txtMProximity.setText(String.valueOf(0));
        txtMGyro.setText(String.valueOf(0));
    }
}
