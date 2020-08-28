package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class GameListActivity extends AppCompatActivity {

    myDbAdapter helper;
    TextView mTextMessage, oTitleView, mDefaultMessage;
    ListView oGameList;
    ArrayList<GamePreview> listOfRows;
    ArrayAdapter<GamePreview> itemAdapter;

    private FirebaseAuth mAuth;

    private RecyclerView mRecyclerView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(GameListActivity.this, HomeActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_account:
                    Intent account = new Intent(GameListActivity.this, AccountActivity.class);
                    startActivity(account);
                    break;
                case R.id.navigation_logout:
                    SaveSharedPreferences.setLoggedIn(getApplicationContext(), false);
                    mAuth.signOut();
                    Intent login = new Intent(GameListActivity.this, MainActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(login);
                    finish();
                    break;
            }
            return false;
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getUid();

        // Initialize DB
        helper = new myDbAdapter(this);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Show home view
        setContentView(R.layout.activity_gamelist);

        // Handle the bottom menu clicks
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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
