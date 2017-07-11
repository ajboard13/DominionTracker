package com.example.android.dominiontracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;


public class AddGameActivity extends AppCompatActivity  {
    private Button addGameButton;
    private int numPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        String[] gameTypeList = getResources().getStringArray(R.array.game_type);
        final String[] playerList = getResources().getStringArray(R.array.players);
        Spinner winnerSpinner = (Spinner) findViewById(R.id.list_of_players);
        Spinner gameTypeSpinner = (Spinner) findViewById(R.id.players_spinner);
        populateSpinners(gameTypeList, gameTypeSpinner);
        populateSpinners(playerList, winnerSpinner);
        final ArrayList<PlayerSpinners> playerSpinners = populateListOfSpinners(playerList);
        gameTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numPlayers = i+2;
                showPlayersToSelect(playerSpinners);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                showPlayersToSelect(playerSpinners);
            }
        });

    }

    private ArrayList<PlayerSpinners> populateListOfSpinners(String[] playerList){
        ArrayList<PlayerSpinners> playerSpinners = new ArrayList<>();
        playerSpinners.add(new PlayerSpinners((Spinner) findViewById(R.id.player1spinner), playerList));
        playerSpinners.add(new PlayerSpinners((Spinner) findViewById(R.id.player2spinner), playerList));
        playerSpinners.add(new PlayerSpinners((Spinner) findViewById(R.id.player3spinner), playerList));
        playerSpinners.add(new PlayerSpinners((Spinner) findViewById(R.id.player4spinner), playerList));
        populateSpinners(playerList, playerSpinners.get(0).getSpinner());
        populateSpinners(playerList, playerSpinners.get(1).getSpinner());
        populateSpinners(playerList, playerSpinners.get(2).getSpinner());
        populateSpinners(playerList, playerSpinners.get(3).getSpinner());
        return playerSpinners;
    }

    private void showPlayersToSelect(ArrayList<PlayerSpinners> spinners){
        switch (numPlayers){
            case 2:
                spinners.get(3).getSpinner().setVisibility(View.INVISIBLE);
                spinners.get(2).getSpinner().setVisibility(View.INVISIBLE);
                break;
            case 3:
                spinners.get(3).getSpinner().setVisibility(View.INVISIBLE);
                spinners.get(2).getSpinner().setVisibility(View.VISIBLE);
                break;
            case 4:
                spinners.get(3).getSpinner().setVisibility(View.VISIBLE);
                spinners.get(2).getSpinner().setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private void populateSpinners(String[] list, Spinner spinner){
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    private void addGame(){

    }
}
