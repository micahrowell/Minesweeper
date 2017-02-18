package com.example.micahj.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String s = getIntent().getStringExtra("restart");

        Intent intent;

        switch (s){
            case "beginner":
                intent = new Intent(Game.this, Beginner.class);
                break;
            case "intermediate":
                intent = new Intent(Game.this, Intermediate.class);
                break;
            case "advanced":
                intent = new Intent(Game.this, Advanced.class);
                break;
            default:
                intent = new Intent(Game.this, MainActivity.class);
        }

        startActivity(intent);
    }
}
