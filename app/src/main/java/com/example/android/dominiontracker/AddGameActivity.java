package com.example.android.dominiontracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Arrays;


public class AddGameActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference databaseReference;
    private Button addGameButton;
    private int numPlayers;
    private String gameType;
    private FirebaseAuth firebaseAuth;
     String[] playerList;
    ArrayList<PlayerSpinners> playerSpinners;
    Spinner winnerSpinner;
    int gameNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        String[] gameTypeList = getResources().getStringArray(R.array.game_type);
        playerList = getResources().getStringArray(R.array.players);
        Spinner gameTypeSpinner = (Spinner) findViewById(R.id.players_spinner);
        winnerSpinner  = (Spinner) findViewById(R.id.list_of_players);
        playerSpinners  = populateListOfSpinners(playerList);
        populateSpinners(gameTypeList, gameTypeSpinner);
        populateSpinners(playerList, winnerSpinner);
        firebaseAuth = FirebaseAuth.getInstance();
        addGameButton = (Button) findViewById(R.id.add_game_button);

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


        databaseReference = FirebaseDatabase.getInstance().getReference();
        addGameButton.setOnClickListener(this);



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
                gameType = "Two Player";
                break;
            case 3:
                spinners.get(3).getSpinner().setVisibility(View.INVISIBLE);
                spinners.get(2).getSpinner().setVisibility(View.VISIBLE);
                gameType = "Three Player";
                break;
            case 4:
                spinners.get(3).getSpinner().setVisibility(View.VISIBLE);
                spinners.get(2).getSpinner().setVisibility(View.VISIBLE);
                gameType = "Four Player";
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

    private void addGame(ArrayList<PlayerSpinners> spinners, Spinner winnerSpinner){
        String[] players = new String[numPlayers];
        EditText dateEditText = (EditText) findViewById(R.id.dateEditText);
        String date = dateEditText.getText().toString();
        String winner = winnerSpinner.getSelectedItem().toString();
        for (int i = 0; i < numPlayers; i++) {
            players[i] = spinners.get(i).getItemName();
        }
        Game game = new Game(gameType, Arrays.asList(players), winner, date);
        createUniqueGameID();

        String gameID = "Game"+gameNum;

        databaseReference.child("Games").child(gameID).setValue(game);
        System.out.println("Game Added");

    }

    private void createUniqueGameID() {
        Query myRef = databaseReference.child("Games");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                gameNum++;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                gameNum--;
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == addGameButton){
            addGame(playerSpinners, winnerSpinner);
        }
    }


}
