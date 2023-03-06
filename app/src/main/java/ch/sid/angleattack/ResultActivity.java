package ch.sid.angleattack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button menuButton = findViewById(R.id.backToMenu);
        menuButton.setOnClickListener(v -> backToMenu());

        Bundle bundle = getIntent().getExtras();
        double score = bundle.getDouble("score");

        TextView textView = findViewById(R.id.scoreValue);
        textView.setText(String.valueOf(score + "s"));
    }

    private void backToMenu() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}