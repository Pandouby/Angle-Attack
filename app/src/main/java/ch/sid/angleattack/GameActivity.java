package ch.sid.angleattack;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor rotationSensor;
    private float[] rotationMatrix = new float[9];
    private float[] orientationValues = new float[3];
    private double randomAngle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_NORMAL);

        randomAngle = Math.round((Math.random()*360) - 180);

        TextView textView = findViewById(R.id.angleToMatch);
        textView.setText(String.valueOf(randomAngle + "°"));
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
            SensorManager.getOrientation(rotationMatrix, orientationValues);
            // Convert the orientation values from radians to degrees
            float azimuth = (float) Math.toDegrees(orientationValues[0]);

            TextView textView = findViewById(R.id.currentAngle);
            textView.setText(String.valueOf(azimuth + "°"));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}