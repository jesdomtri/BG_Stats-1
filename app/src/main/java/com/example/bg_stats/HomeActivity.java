package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class HomeActivity extends AppCompatActivity {

    TextView oTextView, mTextMessage;
    private FirebaseAuth mAuth;

    private RecyclerView mRecyclerView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    break;
                case R.id.navigation_account:
                    Intent account = new Intent(HomeActivity.this, AccountActivity.class);
                    startActivity(account);
                    break;
                case R.id.navigation_logout:
                    SaveSharedPreferences.setLoggedIn(getApplicationContext(), false);
                    mAuth.signOut();
                    Intent login = new Intent(HomeActivity.this, MainActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(login);
                    finish();
                    break;
                case R.id.navigation_utility:
                    Intent utility = new Intent(HomeActivity.this, UtilitiesActivity.class);
                    startActivity(utility);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid();

        if(userID.equals("sqAQj1peQBhSMgJM1OAIdVTw3wz1")){
            Toast.makeText(HomeActivity.this, "Juanma, ¿eres bobo o qué?. Lee el título.", Toast.LENGTH_LONG).show();
        }

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Show home view
        setContentView(R.layout.activity_home);

        mRecyclerView = findViewById(R.id.exp_list);
        new FirebaseDatabaseHelper().readUserGames(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Game> games, List<String> keys) {
                findViewById(R.id.loading_your_games).setVisibility(View.GONE);
                TreeMap<Game, String> sortedGamesMap = new TreeMap<>();
                for (int i = 0; i<games.size(); i++){
                    sortedGamesMap.put(games.get(i), keys.get(i));
                }
                new RecyclerView_Config().setConfig(mRecyclerView, HomeActivity.this, new ArrayList<>(sortedGamesMap.keySet()), new ArrayList<>(sortedGamesMap.values()));
                if (games.isEmpty()) {
                    findViewById(R.id.noGames_home).setVisibility(View.VISIBLE);
                }
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

        // If user click on "Change your games", redirect to account option to do so
        oTextView = findViewById(R.id.change_games_home);
        oTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeGames = new Intent(HomeActivity.this, ChangeGamesActivity.class);
                startActivity(changeGames);
            }
        });

        // Handle the bottom menu clicks
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(0).setChecked(true);

    }
}
