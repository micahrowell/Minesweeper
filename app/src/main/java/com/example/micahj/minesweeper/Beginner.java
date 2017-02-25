package com.example.micahj.minesweeper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Beginner extends AppCompatActivity {

    /*
    rowsColumns is the number of rows and columns in the 2D array. Since we'll be playing with
    a square rows = columns so there's no need for two variables.

    mines is the number of mines in the game, for beginner in a 10x10 we want 10 mines

    winTaps keeps track of whether the player has won. Since there are 100 tiles total with 10 mines
    in the game, a win means winTaps == 0

    k & l represent the rows and columbs in the 2D array. They are used in the while loop in the
    onCreate() method so that I can cycle through the 2D array in order to place the mines

    gameSpace is the 2D array representation of the game
    */

    int rowsColumns = 10, mines = 10, winTaps = 90, k = 0, l = 0;
    int[][] gameSpace = new int[rowsColumns][rowsColumns];

    // This will help generate a random number to determine whether or not to place a mine
    Random rand = new Random();

    // The smiley face button that the player can tap to start a new game whenever they want
    ImageButton smiley;

    // The phone will vibrate when the player trips a mine
    Vibrator vibrator;

    // The phone will also play an explosion sound when the player trips the mine
    MediaPlayer bomb;

    Timer timer;

    boolean flagEnabled = false;

    public void flag(View view){
        ImageView tile = (ImageView) view;
        if(flagEnabled){
            flagEnabled = false;
            tile.setImageResource(R.drawable.flag);
        }
        else {
            flagEnabled = true;
            tile.setImageResource(R.drawable.flag_press);
        }
    }

    // When the player clicks a tile, this is what happens
    public void tileClick(View view){
        // Not just any view, an ImageView
        ImageView tile = (ImageView) view;

        if(flagEnabled){
            tile.setEnabled(false);
            tile.setImageResource(R.drawable.flag);
            return;
        }

        tile.setEnabled(true);

        /*
        First I get the tag that I've given each tile. To find out the row this tile is in I simply
        divide by 10. To find the column I divide by 10 and use the remainder.
        */
        int tag = Integer.parseInt(tile.getTag().toString());
        int row = tag / 10;
        int column = tag % 10;

        // 1 represents a mine is present on this tile, thus a mine has been tripped
        if(gameSpace[row][column] == 1){

            timer.cancel();

            // vibrate
            vibrator.vibrate(100);

            // play the explosion sound
            bomb.start();

            // display a visual mine for the user
            tile.setImageResource(R.drawable.mine);

            // change the smiley button to the dead face
            smiley.setImageResource(R.drawable.smiley_dead);

            // Display a dialog telling the user they have lost and giving them the option of
            // trying again or exiting to the main menu
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
            /*
            This will keep track of how many mines are surrounding this tile. That way I know which
            image to place here so that the player can make the right decision.
             */
            int totalMines = 0;

            // Checking every surrounding tile
            for(int i = row - 1; i <= row + 1; i++){
                for(int j = column - 1; j <= column + 1; j++){

                    // if i or j go out of bounds then do nothing
                    if(i == -1 || i == 10)
                        continue;
                    if(j == -1 || j == 10)
                        continue;

                    // Otherwise add 1 to totalMines
                    totalMines += gameSpace[i][j];
                }
            }

            /*
            After all surrounding tiles have been checked I place the appropriate image on the tile
            so the user can select the right tile
             */
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

            // Tick off a value from winTap to keep track of the player's "score"
            winTaps--;

            /*
            If winTaps == 0 the player has won and I display a dialog letting them know and giving
            them an option to play again or not
            */
            if(winTaps == 0){
                timer.cancel();
                new AlertDialog.Builder(Beginner.this)
                        .setTitle("You won!")
                        .setMessage("Play again?")
                        // Ich spreche gern Deutsch
                        .setPositiveButton("Ja!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                newGame();
                            }
                        })
                        .setNegativeButton("Nein!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        }

        // Disabling the tile from being clicked to prevent winTaps from prematurely reaching 0
        tile.setEnabled(false);
    }

    /*
    This function simply finishes the current activity and restarts it so that the player can play
    another exhilarating round
    */
    public void newGame(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);

        Typeface digital_font = Typeface.createFromAsset(getAssets(), "fonts/DS-DIGI.TTF");

        TextView timeUnderlay = (TextView) findViewById(R.id.timerUnderlay);
        final TextView showTime = (TextView) findViewById(R.id.timer);
        showTime.setTypeface(digital_font);
        timeUnderlay.setTypeface(digital_font);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            int time = 0;

            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showTime.setText(Integer.toString(time));
                    }
                });

                time++;

            }
        }, 1000, 1000);

        // Initiating all necessary buttons, sounds, vibrators, yada yada yada...
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        bomb = MediaPlayer.create(this, R.raw.explode);

        smiley = (ImageButton) findViewById(R.id.smileyButton);

        // If the smiley is clicked then a new game is started
        smiley.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                newGame();
            }
        });

        // Placing 10 random mines in the 2D array
        while(mines > 0){


            // Generating a random number out of 500.
            int n = rand.nextInt(500);

            /*
            I chose 5 because why not. Any number would work, really. I'm also making sure there's
            not already a mine at the given tile
            */
            if(n == 5 && gameSpace[l%rowsColumns][k%rowsColumns] != 1){

                // I decided to do breadth-first traversal so l & k are "backwards"
                gameSpace[l%rowsColumns][k%rowsColumns] = 1;
                mines--;
            }

            /*
            since l represents the columns and I want to do breadth-first I increment it first, then
            when I've reached the last column I increment k to start the next row
            */
            l++;
            if(l%10 == 9)
                k++;
        }

    }
}


