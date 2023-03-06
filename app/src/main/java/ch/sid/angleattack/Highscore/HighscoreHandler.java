package ch.sid.angleattack.Highscore;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.time.LocalDateTime;

public class HighscoreHandler extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private Highscore highscore;

    public HighscoreHandler(Context context) {
        sharedPreferences = context.getSharedPreferences("Data", MODE_PRIVATE);
        //sharedPreferences.edit().clear().apply();
        gson = new Gson();
        if(sharedPreferences.getString("highscore", "") != "") {
            highscore = gson.fromJson(sharedPreferences.getString("highscore", ""), Highscore.class);
        } else {
            highscore = new Highscore(null, 0);
        }
    }

    public void saveHighscore(double time) {
        if(highscore.getTime() == 0 || time < highscore.getTime()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            highscore = new Highscore(LocalDateTime.now(), time);
            editor.putString("highscore", gson.toJson(highscore));
            editor.apply();
        }
    }

    public Highscore getHighscore() {
        return highscore;
    }
}
