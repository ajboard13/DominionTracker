package com.example.android.dominiontracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LeaderBoardActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView leaderBoardListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        leaderBoardListview = (ListView) findViewById(R.id.leader_board_listview);
        String[] leaders = getResources().getStringArray(R.array.leaders);
        ArrayAdapter<String> leaderBoardAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, leaders);

        leaderBoardListview.setAdapter(leaderBoardAdapter);
    }

    @Override
    public void onClick(View view) {

    }

}
