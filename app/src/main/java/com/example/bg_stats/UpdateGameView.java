package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class UpdateGameView extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button addNewMatch, matchesList, deleteGame;

    private TextView titleGame;

    private TextView totalMatchesCount, victoriesCount, defeatsCount;

    private String rKey;
    private String rTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Set content view
        setContentView(R.layout.activity_update_game_view);

        // Actual User
        mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid();

        // Getting Intent
        rKey = getIntent().getStringExtra("rKey");
        rTitle = getIntent().getStringExtra("rTitle");

        // Attributes
        addNewMatch = findViewById(R.id.addNewMatch_guv);
        matchesList = findViewById(R.id.matchesList_guv);
        deleteGame = findViewById(R.id.deleteGame_guv);

        titleGame = findViewById(R.id.titleGame_guv);
        titleGame.setText(rTitle);

        totalMatchesCount = findViewById(R.id.totalMatchesCount_guv);
        victoriesCount = findViewById(R.id.victoriesCount_guv);
        defeatsCount = findViewById(R.id.defeatsCount_guv);

        new FirebaseDatabaseHelper().readMatches(userID, rTitle, new FirebaseDatabaseHelper.DataStatus() {
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
                totalMatchesCount.setText(totalMatches.toString());
                victoriesCount.setText(totalWins.toString());
                Integer defeats = totalMatches - totalWins;
                defeatsCount.setText(defeats.toString());
            }
        });

        addNewMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateGameView.this, AddGameActivity.class);
                intent.putExtra("rTitle", rTitle);
                startActivity(intent);
            }
        });

        matchesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateGameView.this, GameListActivity.class);
                intent.putExtra("rTitle", rTitle);
                startActivity(intent);
            }
        });

        deleteGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHelper().deleteGame(rKey, new FirebaseDatabaseHelper.DataStatus() {

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
                        String msg = "" + titleGame.getText().toString() + " has been deleted successfully.";
                        Toast.makeText(UpdateGameView.this, msg, Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }

                    @Override
                    public void UsersAreLoaded(List<User> allUsers, List<String> keys) {

                    }

                    @Override
                    public void MatchesAreLoaded(Integer totalMatches, Integer totalWins, List<Integer> positionMatches, List<String> scoreMatches, List<Boolean> winnerMatches) {

                    }
                });
            }
        });

    }

}