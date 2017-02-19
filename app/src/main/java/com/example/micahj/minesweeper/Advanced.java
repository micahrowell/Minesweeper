package com.example.micahj.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by micah.J on 2/18/2017.
 */

public class Advanced extends AppCompatActivity {

    TextView text = (TextView) findViewById(R.id.comingSoon);

    public void newAdvGame(View view){
        Intent intent = new Intent(Advanced.this, Game.class);
        intent.putExtra("restart", "advanced");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);

        text.setText(R.string.Coming_Soon);

    }
}
