package com.example.micahj.minesweeper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by micah.J on 2/28/2017.
 */

public class HighScores extends AppCompatActivity {

    SQLiteDatabase db;

    ArrayList<String> scores;

    ListView showScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        scores = new ArrayList<>();

        showScores = (ListView) findViewById(R.id.showScores);

        try{

            db = this.openOrCreateDatabase("High Scores", MODE_PRIVATE, null);

            Cursor c = db.rawQuery("SELECT * FROM scores", null);

            int nameIndex = c.getColumnIndex("name"),
                scoreIndex = c.getColumnIndex("score");

            c.moveToFirst();

            while(c != null){

                String highScore = "";

                highScore += c.getString(nameIndex);
                highScore += "\t\t";
                highScore += c.getString(scoreIndex);

                scores.add(highScore);

                c.moveToNext();

            }

        } catch (Exception e){
            e.printStackTrace();
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scores);
        showScores.setAdapter(arrayAdapter);

    }

}
