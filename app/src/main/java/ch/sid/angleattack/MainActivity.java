package ch.sid.angleattack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

import ch.sid.angleattack.Highscore.Highscore;
import ch.sid.angleattack.Highscore.HighscoreHandler;

public class MainActivity extends AppCompatActivity {
    private HighscoreHandler highscoreHandler;
    private Highscore highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        highscoreHandler = new HighscoreHandler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calcButton = findViewById(R.id.startButton);
        calcButton.setOnClickListener(v -> startGame());

        highscore = highscoreHandler.getHighscore();

        TextView textView = findViewById(R.id.highscoreValue);
        textView.setText(String.valueOf(highscore.getTime() + "s"));
    }

    private void startGame() {
        Intent i = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();

        bundle.putLong("startTime", LocalTime.now().getLong(ChronoField.MILLI_OF_SECOND));
        i.putExtras(bundle);

        startActivity(i);
    }
}