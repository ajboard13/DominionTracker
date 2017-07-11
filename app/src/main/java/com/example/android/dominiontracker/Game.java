package com.example.android.dominiontracker;

/*
Aaron Board

7/10/2017
 */

import java.util.List;

public class Game {
    public String gameType;
    public List<String> players;
    public String winner;
    public String date;

    public Game(){

    }

    public Game(String gameType, List<String> players, String winner, String date) {
        this.gameType = gameType;
        this.players = players;
        this.winner = winner;
        this.date = date;
    }

}
