package com.example.android.dominiontracker;

/*
Aaron Board

7/10/2017
 */

import android.widget.Spinner;

public class PlayerSpinners {
    Spinner spinner;
    String[] list;

    public PlayerSpinners(Spinner spinner, String[] list) {
        this.spinner = spinner;
        this.list = list;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public String[] getList() {
        return list;
    }

    public String getItemName(){
        return spinner.getSelectedItem().toString();
    }
}
