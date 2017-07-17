package com.example.android.dominiontracker;

/**
 * Created by user on 7/17/2017.
 */

public class Player {
    private String userName = "";
    private String email;
    private boolean isAdmin = false;
    private int twoPlayerWins = 0;
    private int threePlayerWins = 0;
    private int fourPlayerWins = 0;
    private int totalWins = 0;

    public Player(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getTwoPlayerWins() {
        return twoPlayerWins;
    }

    public void setTwoPlayerWins(int twoPlayerWins) {
        this.twoPlayerWins = twoPlayerWins;
    }

    public int getThreePlayerWins() {
        return threePlayerWins;
    }

    public void setThreePlayerWins(int threePlayerWins) {
        this.threePlayerWins = threePlayerWins;
    }

    public int getFourPlayerWins() {
        return fourPlayerWins;
    }

    public void setFourPlayerWins(int fourPlayerWins) {
        this.fourPlayerWins = fourPlayerWins;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }
}
