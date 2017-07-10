package com.example.android.dominiontracker;

/*
Aaron Board

7/10/2017
 */

public class Game {
    public GameType gameType;
    public String winner;
    public String date;

    public Game(){

    }

    public Game(GameType gameType, String winner, String date) {
        this.gameType = gameType;
        this.winner = winner;
        this.date = date;
    }
}
