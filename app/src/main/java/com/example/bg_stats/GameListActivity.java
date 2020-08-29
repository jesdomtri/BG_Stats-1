package com.example.bg_stats;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class GameListActivity extends AppCompatActivity {

    TextView mTextMessage, oTitleView;

    private FirebaseAuth mAuth;

    private RecyclerView mRecyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getUid();

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Show home view
        setContentView(R.layout.activity_gamelist);

        // Set title
        String selectedGame = getIntent().getStringExtra("rTitle");
        oTitleView = findViewById(R.id.gamelist_title);
        oTitleView.setText(selectedGame);

        // Set adapter
        mRecyclerView = findViewById(R.id.gameList);
        new FirebaseDatabaseHelper().readMatches(userID, selectedGame, new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Game> games, List<String> keys) {

            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }

            @Override
            public void UsersAreLoaded(List<User> allUsers, List<String> keys) {

            }

            @Override
            public void MatchesAreLoaded(Integer totalMatches, Integer totalWins, List<Integer> positionMatches, List<String> scoreMatches, List<Boolean> winnerMatches) {
                List<Match> newMatchesList = new ArrayList<>();
                for (int i = 0; i < positionMatches.size(); i++) {
                    Match match = new Match(scoreMatches.get(i), positionMatches.get(i), winnerMatches.get(i));
                    newMatchesList.add(match);
                }
                new RecyclerView_Config().setConfigMatches(mRecyclerView, GameListActivity.this, newMatchesList);
            }
        });


    }
}
