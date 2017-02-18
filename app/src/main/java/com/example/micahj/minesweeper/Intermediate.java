package com.example.micahj.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by micah.J on 2/18/2017.
 */

public class Intermediate extends AppCompatActivity {

    public void newGame(View view){
        Intent intent = new Intent(Intermediate.this, Game.class);
        intent.putExtra("restart", "intermediate");
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);



    }
}
