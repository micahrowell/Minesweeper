package com.example.micahj.minesweeper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

/**
 * Created by micah.J on 2/28/2017.
 */

public class HighScores extends AppCompatActivity {

    SQLiteDatabase db;

    ArrayList<String> begScores,
                      intScores,
                      advScores;

    ListView showBegScores,
             showIntScores,
             showAdvScores;

    TabHost host;

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        begScores = new ArrayList<>();
        intScores = new ArrayList<>();
        advScores = new ArrayList<>();

        host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        // Beginner Tab
        TabHost.TabSpec spec = host.newTabSpec("Beginner Tab");
        spec.setContent(R.id.showBegScores);
        spec.setIndicator("Easy");
        host.addTab(spec);

        // Intermediate Tab
        spec = host.newTabSpec("Intermediate Tab");
        spec.setContent(R.id.showIntScores);
        spec.setIndicator("Medium");
        host.addTab(spec);

        // Advanced Tab
        spec = host.newTabSpec("Advanced Tab");
        spec.setContent(R.id.showAdvScores);
        spec.setIndicator("Hard");
        host.addTab(spec);

        showBegScores = (ListView) findViewById(R.id.showBegScores);
        showIntScores = (ListView) findViewById(R.id.showIntScores);
        showAdvScores = (ListView) findViewById(R.id.showAdvScores);

        try{

            db = this.openOrCreateDatabase("High Scores", MODE_PRIVATE, null);

            Cursor c = db.rawQuery("SELECT * FROM beginner ORDER BY score * 1 ASC", null);

            int nameIndex = c.getColumnIndex("name"),
                scoreIndex = c.getColumnIndex("score");

            c.moveToFirst();

            while(c != null){

                String highScore = "";

                highScore += c.getString(nameIndex);
                highScore += "\t\t";
                highScore += c.getString(scoreIndex);

                begScores.add(highScore);

                c.moveToNext();

            }

            c.close();

        } catch (Exception e){
            e.printStackTrace();
            Log.i("error", e.toString());
        }

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, begScores);
        showBegScores.setAdapter(arrayAdapter);



        try{

            db = this.openOrCreateDatabase("High Scores", MODE_PRIVATE, null);

            Cursor c = db.rawQuery("SELECT * FROM intermediate ORDER BY score * 1 ASC", null);

            int nameIndex = c.getColumnIndex("name"),
                    scoreIndex = c.getColumnIndex("score");

            c.moveToFirst();

            while(c != null){

                String highScore = "";

                highScore += c.getString(nameIndex);
                highScore += "\t\t";
                highScore += c.getString(scoreIndex);

                intScores.add(highScore);

                c.moveToNext();

            }

            c.close();

        } catch (Exception e){
            e.printStackTrace();
            Log.i("error", e.toString());
        }

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, intScores);
        showIntScores.setAdapter(arrayAdapter);



        try{

            db = this.openOrCreateDatabase("High Scores", MODE_PRIVATE, null);

            Cursor c = db.rawQuery("SELECT * FROM advanced ORDER BY score * 1 ASC", null);

            int nameIndex = c.getColumnIndex("name"),
                    scoreIndex = c.getColumnIndex("score");

            c.moveToFirst();

            while(c != null){

                String highScore = "";

                highScore += c.getString(nameIndex);
                highScore += "\t\t";
                highScore += c.getString(scoreIndex);

                advScores.add(highScore);

                c.moveToNext();

            }

            c.close();

        } catch (Exception e){
            e.printStackTrace();
            Log.i("error", e.toString());
        }

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, advScores);
        showAdvScores.setAdapter(arrayAdapter);

    }

}
