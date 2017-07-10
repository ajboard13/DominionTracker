package com.example.android.dominiontracker;

/**
 * Created by Aaron on 7/10/2017.
 */

public enum GameType {
    TWOPLAYER("Two Player"), THREEPLAYER("Three Player"), FOURPLAYER("Four Player");

    private String properName;

    GameType(String properName) {
        this.properName = properName;
    }

    @Override
    public String toString() {
        return properName;
    }
}
