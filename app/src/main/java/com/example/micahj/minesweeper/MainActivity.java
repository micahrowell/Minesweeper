package com.example.micahj.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button beginner;
    private Button intermediate;
    private Button advanced;
    private Button highScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beginner = (Button) findViewById(R.id.beginner_button);
        intermediate = (Button) findViewById(R.id.intermediate_button);
        advanced = (Button) findViewById(R.id.advanced_button);

        highScores = (Button) findViewById(R.id.high_scores_button);

        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Game.class);

                intent.putExtra("mine count", 10);

                startActivity(intent);
            }
        });


        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Game.class);

                intent.putExtra("mine count", 20);

                startActivity(intent);
            }
        });


        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Game.class);

                intent.putExtra("mine count", 30);

                startActivity(intent);
            }
        });


        highScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, HighScores.class);

                startActivity(intent);
            }
        });
    }
}
