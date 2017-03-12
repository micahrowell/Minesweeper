package com.example.micahj.minesweeper;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by micah.J on 2/27/2017.
 */

public class Tile {

    private boolean isMine;
    private boolean clickable;

    //ImageView tile;

    public Tile(){
        isMine = false;
        clickable = true;
        //tile = (ImageView) v;
    }

    public boolean isMine(){
        return isMine;
    }

    public void setMine(){
        isMine = true;
    }

    public boolean isClickable(){
        return clickable;
    }

    public void setClickable(boolean bool){
        clickable = bool;
    }

}
