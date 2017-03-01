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

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);

        text = (TextView) findViewById(R.id.comingSoon);

        text.setText(R.string.Coming_Soon);

    }
}
