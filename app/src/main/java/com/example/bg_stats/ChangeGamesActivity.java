package com.example.bg_stats;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChangeGamesActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private RecyclerView mRecyclerView;

    private RecyclerView_Config.GamesAdapter2 adapter;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String userID = mAuth.getCurrentUser().getUid();

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Show home view
        setContentView(R.layout.activity_change_games);

        mRecyclerView = findViewById(R.id.listGames);
        new FirebaseDatabaseHelper().readGames(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Game> games, List<String> keys) {
                findViewById(R.id.loading_all_games).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig2(mRecyclerView, ChangeGamesActivity.this, games, keys);
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

            }
        });

        searchView = findViewById(R.id.searchGame);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                new FirebaseDatabaseHelper().readGames(new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Game> games, List<String> keys) {
                        findViewById(R.id.loading_all_games).setVisibility(View.GONE);
                        List<Game> newList = new ArrayList<>();
                        for (Game game : games) {
                            if (game.getName().toLowerCase().startsWith(newText.toLowerCase())) {
                                newList.add(game);
                            }
                        }
                        new RecyclerView_Config().setConfig2(mRecyclerView, ChangeGamesActivity.this, newList, keys);
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

                    }
                });
                return false;
            }
        });
    }
}