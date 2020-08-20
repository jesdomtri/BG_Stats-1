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

public class AddGameView extends AppCompatActivity {

    private Button addGame;

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
        setContentView(R.layout.activity_add_game_view);

        // Getting Intent
        rKey = getIntent().getStringExtra("rKey");
        rTitle = getIntent().getStringExtra("rTitle");

        // Attributes
        addGame = findViewById(R.id.addGame_gav);

        titleGame = findViewById(R.id.titleGame_gav);
        titleGame.setText(rTitle);

        totalMatchesCount = findViewById(R.id.totalMatchesCount_gav);
        victoriesCount = findViewById(R.id.victoriesCount_gav);
        defeatsCount = findViewById(R.id.defeatsCount_gav);


        addGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game game = new Game();
                game.setName(titleGame.getText().toString());
                new FirebaseDatabaseHelper().addGame(game, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Game> games, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Intent home = new Intent(AddGameView.this, HomeActivity.class);
                        home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(home);

                        String msg = "" + titleGame.getText().toString() + " has been added successfully.";
                        Toast.makeText(AddGameView.this, msg, Toast.LENGTH_LONG).show();

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
    }
}