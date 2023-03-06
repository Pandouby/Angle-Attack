package ch.sid.angleattack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.time.format.DateTimeFormatter;

import ch.sid.angleattack.Highscore.Highscore;
import ch.sid.angleattack.Highscore.HighscoreHandler;

public class MainActivity extends AppCompatActivity {
    private HighscoreHandler highscoreHandler;
    private Highscore highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        highscoreHandler = new HighscoreHandler(getBaseContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calcButton = findViewById(R.id.startButton);
        calcButton.setOnClickListener(v -> startGame());

        highscore = highscoreHandler.getHighscore();

        TextView textView = findViewById(R.id.highscoreValue);
        textView.setText(String.valueOf(highscore.getTime() + "s"));

        TextView highscoreDate = findViewById(R.id.highscoreDate);
        highscoreDate.setText(String.valueOf(highscore.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }

    private void startGame() {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }
}