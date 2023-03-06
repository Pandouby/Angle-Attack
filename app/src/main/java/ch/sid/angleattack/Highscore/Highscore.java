package ch.sid.angleattack.Highscore;

import android.content.SharedPreferences;

import java.time.LocalDateTime;

public class Highscore {
    private String creationDate;
    private double time;

    public Highscore(String creationDate, double time) {
        this.creationDate = creationDate;
        this.time = time;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public double getTime() {
        return time;
    }
}
