package com.example.android.dominiontracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonSignOut;
    private Button buttonAddGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonSignOut = (Button) findViewById(R.id.buttonSignOut);
        buttonAddGame = (Button) findViewById(R.id.buttonAddGame);
        ListView gameTypeList = (ListView) findViewById(R.id.games_listview);


        String[] gameTypes = getResources().getStringArray(R.array.game_types);
        final ArrayAdapter<String> gameTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                                                                            gameTypes);

        gameTypeList.setAdapter(gameTypeAdapter);

        gameTypeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(ProfileActivity.this, LeaderBoardActivity.class);
                startActivity(appInfo);
            }
        });

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail.setText("Welcome " + user.getEmail());

        buttonSignOut.setOnClickListener(this);
        buttonAddGame.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        if (view == buttonSignOut) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (view == buttonAddGame){
            startActivity(new Intent(this, AddGameActivity.class));
        }
    }


}
