package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddGameActivity extends AppCompatActivity {

    String username;
    TextView oTextView, oPlayer1;
    ArrayList<String> userList;
    ArrayList<String> userIdList;
    ArrayList<String> scores;
    ArrayList<String> players;
    ArrayAdapter<String> playerAdapter;
    AutoCompleteTextView autoCompleteTextView2, autoCompleteTextView3, autoCompleteTextView4, autoCompleteTextView5, autoCompleteTextView6, autoCompleteTextView7, autoCompleteTextView8;
    EditText score1, score2, score3, score4, score5, score6, score7, score8, extra_text;
    Button confirm_changes, cancel_changes;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Show home view
        setContentView(R.layout.activity_addgame);

        // Set title text
        oTextView = findViewById(R.id.popup_title);
        oTextView.setText(getIntent().getStringExtra("rTitle"));

        // Get all users and set suggestion when writing others players
        oPlayer1 = findViewById(R.id.player1);
        oPlayer1.setText(mAuth.getCurrentUser().getEmail());
        oPlayer1.setEnabled(false);

        username = mAuth.getCurrentUser().getEmail();

        userList = new ArrayList<>();
        userIdList = new ArrayList<>();

        new FirebaseDatabaseHelper().readUsers(new FirebaseDatabaseHelper.DataStatus() {
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
                userList.clear();
                userIdList.clear();
                for (User user : allUsers) {
                    userList.add(user.getEmail());
                    userIdList.add(user.getId());
                }
            }

            @Override
            public void MatchesAreLoaded(Integer totalMatches, Integer totalWins, List<Integer> positionMatches, List<String> scoreMatches, List<Boolean> winnerMatches) {

            }
        });

        playerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, userList);
        initializeObjects();

        // When user click add game...
        confirm_changes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Get the values given by user
                scores = new ArrayList<>(Arrays.asList(score1.getText().toString(), score2.getText().toString(),
                        score3.getText().toString(), score4.getText().toString(), score5.getText().toString(), score6.getText().toString(), score7.getText().toString(),
                        score8.getText().toString()));

                players = new ArrayList<>(Arrays.asList(username, autoCompleteTextView2.getText().toString(), autoCompleteTextView3.getText().toString(),
                        autoCompleteTextView4.getText().toString(), autoCompleteTextView5.getText().toString(), autoCompleteTextView6.getText().toString(),
                        autoCompleteTextView7.getText().toString(), autoCompleteTextView8.getText().toString()));

                // Check text given by user
                boolean AllChecked = true;
                Integer number_players = 0;
                String error = "";
                for (int index = 0; index < players.size(); index++) {
                    if (userList.contains(players.get(index)) && (scores.get(index).equals(""))) { // User correct, void score
                        error += "User '" + players.get(index) + "' does not have score attached \n";
                        AllChecked = false;
                    } else if (checkForDuplicates(players)) {
                        error += "There are repeated usernames \n";
                        AllChecked = false;
                    } else if (players.get(index).equals("")) { // User void
                        scores.set(index, "");
                        number_players += 1;
                    } else if (!(userList.contains(players.get(index)))) {
                        error += "User '" + players.get(index) + "' does not exist \n";
                        AllChecked = false;
                    }
                }
                if (number_players == players.size() - 1) {
                    AllChecked = false;
                    error += "There must be at least 2 players";
                }


                // If something is wrong, show error and stop process
                if (!(AllChecked)) {
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                } else {

                    HashMap<String, Integer> scoreByUser = new HashMap<>();

                    for (int i = 0; i < players.size(); i++) {
                        if (!players.get(i).equals("") || !scores.get(i).equals("")) {
                            scoreByUser.put(players.get(i), Integer.valueOf(scores.get(i)));
                        }
                    }

                    Object[] sorting = scoreByUser.entrySet().toArray();
                    Arrays.sort(sorting, new Comparator() {
                        public int compare(Object o2, Object o1) {
                            return ((Map.Entry<String, Integer>) o1).getValue().compareTo(
                                    ((Map.Entry<String, Integer>) o2).getValue());
                        }
                    });

                    LinkedHashMap<String, Integer> sortedScoreByUser = new LinkedHashMap<>();

                    for (Object o : sorting) {
                        String key = ((Map.Entry<String, Integer>) o).getKey();
                        Integer value = ((Map.Entry<String, Integer>) o).getValue();
                        sortedScoreByUser.put(key, value);
                    }


                    Integer position = 0;
                    for (Map.Entry<String, Integer> entry : sortedScoreByUser.entrySet()) {
                        String player = entry.getKey();
                        String score = entry.getValue().toString();
                        position++;

                        Integer positionUserInUserList = userList.indexOf(player);
                        String userId = userIdList.get(positionUserInUserList);

                        String gameTitle = getIntent().getStringExtra("rTitle");

                        if (position == 1) {
                            new FirebaseDatabaseHelper().addMatch(userId, gameTitle, score, position, Boolean.TRUE, new FirebaseDatabaseHelper.DataStatus() {
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

                                }
                            });
                        } else {
                            new FirebaseDatabaseHelper().addMatch(userId, gameTitle, score, position, Boolean.FALSE, new FirebaseDatabaseHelper.DataStatus() {
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

                                }
                            });
                        }

                    }


                    // Redirect to Home view
                    Intent home = new Intent(AddGameActivity.this, HomeActivity.class);
                    home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(home);
                    Toast.makeText(getApplicationContext(), " Match added", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });


        cancel_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(AddGameActivity.this, HomeActivity.class);
                home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(home);
                // End login activity
                finish();
            }
        });


    }

    private void initializeObjects() {
        // Initialize autoCompleteTextView
        autoCompleteTextView2 = findViewById(R.id.player2);
        autoCompleteTextView2.setAdapter(playerAdapter);
        autoCompleteTextView3 = findViewById(R.id.player3);
        autoCompleteTextView3.setAdapter(playerAdapter);
        autoCompleteTextView4 = findViewById(R.id.player4);
        autoCompleteTextView4.setAdapter(playerAdapter);
        autoCompleteTextView5 = findViewById(R.id.player5);
        autoCompleteTextView5.setAdapter(playerAdapter);
        autoCompleteTextView6 = findViewById(R.id.player6);
        autoCompleteTextView6.setAdapter(playerAdapter);
        autoCompleteTextView7 = findViewById(R.id.player7);
        autoCompleteTextView7.setAdapter(playerAdapter);
        autoCompleteTextView8 = findViewById(R.id.player8);
        autoCompleteTextView8.setAdapter(playerAdapter);

        // Initialize buttons

        confirm_changes = findViewById(R.id.add_game);
        cancel_changes = findViewById(R.id.cancel_game);

        // Initialize EditText (Score AND Comments)
        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);
        score3 = findViewById(R.id.score3);
        score4 = findViewById(R.id.score4);
        score5 = findViewById(R.id.score5);
        score6 = findViewById(R.id.score6);
        score7 = findViewById(R.id.score7);
        score8 = findViewById(R.id.score8);
    }

    private boolean checkForDuplicates(ArrayList<String> array) {
        // create an empty set
        Set<String> set = new HashSet<>();

        // do for every element in the array
        for (String e : array) {
            // return true if duplicate is found
            if (set.contains(e) && !(e.equals("")))
                return true;

            // insert current element into a set
            if (e != null)
                set.add(e);
        }

        // no duplicate found
        return false;
    }
}