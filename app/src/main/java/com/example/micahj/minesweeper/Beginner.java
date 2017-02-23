package com.example.micahj.minesweeper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

public class Beginner extends AppCompatActivity {

    int rowsColumns = 10, mines = 10, winTaps = 90, k = 0, l = 0;
    int[][] gameSpace = new int[rowsColumns][rowsColumns];

    Random rand = new Random();

    ImageButton smiley;

    Vibrator vibrator;

    MediaPlayer bomb;

    public void tileClick(View view){
        ImageView tile = (ImageView) view;

        int tag = Integer.parseInt(tile.getTag().toString());
        int row = tag / 10;
        int column = tag % 10;

        if(gameSpace[row][column] == 1){
            vibrator.vibrate(100);

            bomb.start();

            tile.setImageResource(R.drawable.mine);

            smiley.setEnabled(true);
            smiley.setImageResource(R.drawable.smiley_dead);

            new AlertDialog.Builder(Beginner.this)
                    .setTitle("U ded.")
                    .setPositiveButton(R.string.playAgain, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            newGame();
                        }
                    })
                    .setNegativeButton(R.string.mainMenu, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }

        else {
            int totalMines = 0;

            for(int i = row - 1; i <= row + 1; i++){
                for(int j = column - 1; j <= column + 1; j++){
                    if(i == -1 || i == 10)
                        continue;
                    if(j == -1 || j == 10)
                        continue;

                    totalMines += gameSpace[i][j];
                }
            }

            switch (totalMines) {
                case 0:
                    tile.setImageResource(R.drawable.empty_tile);
                    break;
                case 1:
                    tile.setImageResource(R.drawable.one);
                    break;
                case 2:
                    tile.setImageResource(R.drawable.two);
                    break;
                case 3:
                    tile.setImageResource(R.drawable.three);
                    break;
                case 4:
                    tile.setImageResource(R.drawable.four);
                    break;
                case 5:
                    tile.setImageResource(R.drawable.five);
                    break;
                case 6:
                    tile.setImageResource(R.drawable.six);
                    break;
                case 7:
                    tile.setImageResource(R.drawable.seven);
                    break;
                case 8:
                    tile.setImageResource(R.drawable.eight);
                    break;
            }

            winTaps--;

            if(winTaps == 0){
                new AlertDialog.Builder(Beginner.this)
                        .setTitle("You won!")
                        .setMessage("Play again?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                newGame();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        }

        tile.setEnabled(false);
    }

    public void newGame(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        bomb = MediaPlayer.create(this, R.raw.explode);

        smiley = (ImageButton) findViewById(R.id.smileyButton);
        smiley.setEnabled(false);
        smiley.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                newGame();
            }
        });

        for(int i = 0; i < rowsColumns; i++){
            for(int j = 0; j < rowsColumns; j++){
                gameSpace[i][j] = 0;
            }
        }

        while(mines > 0){

            int n = rand.nextInt(100);

            if(n == 5 && gameSpace[k%rowsColumns][l%rowsColumns] != 1){
                gameSpace[k%rowsColumns][l%rowsColumns] = 1;
                mines--;
            }

            l++;
            if(l%10 == 9)
                k++;
        }

    }
}


