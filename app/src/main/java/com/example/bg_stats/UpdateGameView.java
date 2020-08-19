package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class UpdateGameView extends AppCompatActivity {

    private Button addNewMatch, addGame, matchesList, deleteGame;

    private TextView titleGame;

    private TextView totalMatches, totalMatchesCount, victories, victoriesCount, defeats, defeatsCount;

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

        // Getting Intent
        rKey = getIntent().getStringExtra("rKey");
        rTitle = getIntent().getStringExtra("rTitle");

        // Attributes
        addNewMatch = findViewById(R.id.addNewMatch_guv);
        matchesList = findViewById(R.id.matchesList_guv);
        deleteGame = findViewById(R.id.deleteGame_guv);
        addGame = findViewById(R.id.addGame_guv);

        titleGame = findViewById(R.id.titleGame_guv);
        titleGame.setText(rTitle);

        totalMatches = findViewById(R.id.totalMatches_guv);
        totalMatchesCount = findViewById(R.id.totalMatchesCount_guv);
        victories = findViewById(R.id.victories_guv);
        victoriesCount = findViewById(R.id.victoriesCount_guv);
        defeats = findViewById(R.id.defeats_guv);
        defeatsCount = findViewById(R.id.defeatsCount_guv);

        addGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game game = new Game();
                game.setGameId(0);
                game.setName(titleGame.getText().toString());
                new FirebaseDatabaseHelper().addGame(game, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Game> games, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Intent home = new Intent(UpdateGameView.this, HomeActivity.class);
                        home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(home);

                        String msg = "" + titleGame.getText().toString() + " has been added successfully.";
                        Toast.makeText(UpdateGameView.this, msg, Toast.LENGTH_LONG).show();

                        finish();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
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
                });
            }
        });

    }

}