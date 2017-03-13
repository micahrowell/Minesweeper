package com.example.micahj.minesweeper;

/**
 * Created by micah.J on 2/27/2017.
 */

public class Tile {

    private boolean isMine;
    private boolean clickable;

    public Tile(){
        isMine = false;
        clickable = true;
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
