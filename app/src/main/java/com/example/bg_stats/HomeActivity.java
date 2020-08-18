package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class HomeActivity extends AppCompatActivity {

    myDbAdapter helper;
    TextView oTextView, mTextMessage, oTitleView;
    ArrayList<String> games = new ArrayList<String>();
    TextView defaultMessage;

    private ExpandableAdapter expandableAdapter;
    private ExpandableListView expList;
    public static ArrayList<ArrayList<String>> childList;
    public String[] parents;

    private ValueEventListener eventListener;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String userID = mAuth.getCurrentUser().getUid();
//        mDatabase.child("Usuarios").child(userID).child("Juegos").setValue("Aaaaaa");

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Show home view
        setContentView(R.layout.activity_home);

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


        // Populate expandable list with user games
        expList = findViewById(R.id.exp_list);
        expList.setClickable(true);

        // Get games and sub-items for every item
        getParentsAndChilds();
        expandableAdapter = new ExpandableAdapter(this, childList, parents);
        expList.setAdapter(expandableAdapter);

        // Set onListener for the sub-menu options
        expList.setOnChildClickListener(new ExpandableListView.
                OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (childPosition == 1) { // Add new game
                    Intent addGame = new Intent(HomeActivity.this, AddGameActivity.class);
                    addGame.putExtra("Game_name", parents[groupPosition]);
                    startActivity(addGame);
                } else if (childPosition == 2) { // Redirect to list of games
                    Intent gameList = new Intent(HomeActivity.this, GameListActivity.class);
                    gameList.putExtra("Game_name", parents[groupPosition]);
                    startActivity(gameList);
                } else if (childPosition == 3) { // Redirect to stats activity
                    Intent gameStats = new Intent(HomeActivity.this, GameStatsActivity.class);
                    gameStats.putExtra("Game_name", parents[groupPosition]);
                    startActivity(gameStats);
                }
                return true;
            }
        });
        // If games selected by user equals 0, show message
        defaultMessage = findViewById(R.id.defaultText);
        if (games.size() == 0) {
            defaultMessage.setVisibility(View.VISIBLE);
        } else {
            defaultMessage.setVisibility(View.INVISIBLE);
        }

    }

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
                    SaveSharedPreferences.setUsername(getApplicationContext(), "");
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

    private void getParentsAndChilds() {
        // Get games
        childList = new ArrayList<>();
        DatabaseReference dbGames = mDatabase.child("Users").child(mAuth.getCurrentUser().getUid()).child("Games");

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();
                ArrayList<Object> valores = new ArrayList<>(td.values());
                for (Object v : valores) {
                    games.add(v.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error", "Error!", databaseError.toException());
            }
        };

        dbGames.addListenerForSingleValueEvent(eventListener);

        parents = new String[games.size()];

        for (int i=0; i<games.size();i++){
            parents[i]=games.get(i);
        }

//        parents = games.toArray();

        // Create sub-menu
        for (int index = 0; index < parents.length; index++) {
            ArrayList<String> aux = new ArrayList<>();
            Integer played = 0;
            Integer wins = 0;
            Integer losses = played - wins;
            aux.add("Total: " + played + " Wins: " + wins + " Losses: " + losses);
            aux.add("Add new game");
            aux.add("See game list");
            aux.add("Your game stats");

            childList.add(aux);
        }

    }
}
