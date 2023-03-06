package ch.sid.angleattack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.TextView;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

import ch.sid.angleattack.Highscore.Highscore;
import ch.sid.angleattack.Highscore.HighscoreHandler;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private Vibrator vibrator;
    private SensorManager sensorManager;
    private Sensor rotationSensor;
    private float[] rotationMatrix = new float[9];
    private float[] orientationValues = new float[3];

    private double randomAngle;
    private HighscoreHandler highscoreHandler;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startTime = System.currentTimeMillis();
        highscoreHandler = new HighscoreHandler(getBaseContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_NORMAL);

        randomAngle = Math.round((Math.random()*360));

        TextView textView = findViewById(R.id.angleToMatch);
        textView.setText(String.valueOf(randomAngle + "°"));
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
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
            double azimuth = (Math.toDegrees(orientationValues[0]) + 360) % 360;
            azimuth = Math.round(azimuth * 100.0) / 100.0;

            TextView textView = findViewById(R.id.currentAngle);
            textView.setText(String.valueOf(azimuth + "°"));

            if(azimuth >= randomAngle - 1 && azimuth <= randomAngle + 1) {
                gameWon();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void gameWon() {
        stop();
        vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        long endTime = System.currentTimeMillis();
        double duration = (endTime- startTime) / 1000;

        highscoreHandler.saveHighscore(duration);

        Intent i = new Intent(this, ResultActivity.class);
        Bundle resultBundle = new Bundle();

        resultBundle.putDouble("score", duration);
        i.putExtras(resultBundle);

        startActivity(i);
    }

    /**
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
     **/
}