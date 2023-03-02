package ch.sid.angleattack.Highscore;

import android.content.SharedPreferences;

import java.time.LocalDateTime;

public class Highscore {
    private LocalDateTime creationDate;
    private double time;

    public Highscore(LocalDateTime creationDate, double time) {
        this.creationDate = creationDate;
        this.time = time;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public double getTime() {
        return time;
    }
}
