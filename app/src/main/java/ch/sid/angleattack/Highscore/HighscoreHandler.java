package ch.sid.angleattack.Highscore;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.time.LocalDateTime;

public class HighscoreHandler extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private Highscore highscore;

    public HighscoreHandler() {
        gson = new Gson();
        highscore = new Highscore(null, 0);
    }

    public void saveHighscore(double time) {
        if(time < highscore.getTime()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            highscore = new Highscore(LocalDateTime.now(), time);
            editor.putString("MyObject", gson.toJson(highscore));
            editor.apply();
        }
    }

    public Highscore getHighscore() {
        return highscore;
    }
}
