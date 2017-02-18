package com.example.micahj.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

public class Beginner extends AppCompatActivity {

    int size = 100, mines = 10, j = 0, winTaps = 90;
    int[] gameSpace = new int[size];

    Random rand = new Random();

    ImageButton smiley;

    public void testTag(View view){
        ImageView tile = (ImageView) view;
        //System.out.println(tile.getTag());
        smiley = (ImageButton) findViewById(R.id.smileyButton);

        int tag = Integer.parseInt(tile.getTag().toString());

        if(gameSpace[tag] == 1){
            tile.setImageResource(R.drawable.mine);
            smiley.setImageResource(R.drawable.smiley_dead);
            //return;
        }
        else {
            int totalMines = 0;

            //if(tag >= 11 && tag <= 18) {

                for (int i = tag - 11; i <= tag - 9; i++)
                    totalMines += gameSpace[i];

                for (int i = tag - 1; i <= tag + 1; i++) {
                    totalMines += gameSpace[i];
                }

                for (int i = tag + 9; i <= tag + 11; i++)
                    totalMines += gameSpace[i];


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
            //}

        }
    }

    public void newGame(View view){
        Intent intent = new Intent(Beginner.this, Game.class);
        intent.putExtra("restart", "beginner");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);

        for(int i = 0; i < size; i++)
            gameSpace[i] = 0;

        while(mines > 0){

            int n = rand.nextInt(100);

            if(n == 5){
                gameSpace[j%size] = 1;
                mines--;
            }

            j++;
        }

    }
}


