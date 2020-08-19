package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AddGameActivity extends AppCompatActivity {

    myDbAdapter helper;
    String SelectedGame, username, commentary;
    TextView oTextView, oPlayer1;
    ArrayList<String> userList;
    ArrayList<String> scores;
    ArrayList<String> players;
    ArrayList<String> parameters;
    ArrayList<Integer> integerScores = new ArrayList<>();
    ArrayAdapter<String> playerAdapter;
    AutoCompleteTextView autoCompleteTextView2, autoCompleteTextView3, autoCompleteTextView4, autoCompleteTextView5, autoCompleteTextView6, autoCompleteTextView7, autoCompleteTextView8;
    EditText score1, score2, score3, score4, score5, score6, score7, score8, extra_text;
    Button confirm_changes, cancel_changes;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DB
        helper = new myDbAdapter(this);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Show home view
        setContentView(R.layout.activity_addgame);

        // Set title text
        oTextView = findViewById(R.id.popup_title);
        SelectedGame = getIntent().getStringExtra("Game_name");
        String final_string = "Adding a new " + SelectedGame + " game";
        oTextView.setText(final_string);

        // Get all users and set suggestion when writing others players
        oPlayer1 = findViewById(R.id.player1);
        username = "";
        oPlayer1.setText(username);
        oPlayer1.setEnabled(false);

        userList = helper.getAllUsers();
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

                commentary = extra_text.getText().toString();

                // Check text given by user
                boolean AllChecked = true;
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
                    } else if (!(userList.contains(players.get(index)))) {
                        error += "User '" + players.get(index) + "' does not exist \n";
                        AllChecked = false;
                    }
                }

                // If something is wrong, show error and stop process
                if (!(AllChecked)) {
                    Toast.makeText(getApplicationContext(),
                            error, Toast.LENGTH_SHORT).show();
                } else {

                    // Order position by score
                    for (int index = 0; index < scores.size(); index++) {
                        if (scores.get(index).equals("")) {
                            integerScores.add(-999);
                        } else {
                            integerScores.add(Integer.parseInt(scores.get(index)));
                        }
                    }
                    ArrayList<Integer> copyOfIntegerScores = new ArrayList<>(integerScores);
                    Collections.sort(copyOfIntegerScores, Collections.reverseOrder());

                    ArrayList<String> finalPlayers = new ArrayList<>(players);
                    ArrayList<String> finalScores = new ArrayList<>(scores);

                    for (int index = 0; index < copyOfIntegerScores.size(); index++) {
                        if (copyOfIntegerScores.get(index) == -999) {
                            int newPosition = copyOfIntegerScores.indexOf(integerScores.get(index));
                            finalScores.set(newPosition, "");
                            finalPlayers.set(newPosition, players.get(index));
                        } else {
                            int newPosition = copyOfIntegerScores.indexOf(integerScores.get(index));
                            finalScores.set(newPosition, integerScores.get(index).toString());
                            finalPlayers.set(newPosition, players.get(index));
                        }
                    }

                    // Join the final results in one array and add the game
                    parameters = new ArrayList<>();
                    parameters.add(SelectedGame);
                    parameters.addAll(finalPlayers);
                    parameters.addAll(finalScores);
                    parameters.add(commentary);

                    // Set today date and save in table as day/month/year
                    int style = DateFormat.SHORT;
                    DateFormat df = DateFormat.getDateInstance(style, Locale.UK);
                    String newEntry;
                    newEntry = df.format(new Date());

                    parameters.add(newEntry);

                    helper.setNewGame(parameters);

                    // Redirect to Home view
                    Intent home = new Intent(AddGameActivity.this, HomeActivity.class);
                    home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(home);
                    Toast.makeText(getApplicationContext(),
                            SelectedGame + " game added", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        // When user click cancel...

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
        extra_text = findViewById(R.id.extra_text);
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
