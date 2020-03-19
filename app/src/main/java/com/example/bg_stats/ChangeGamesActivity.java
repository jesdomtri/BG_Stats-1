package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ChangeGamesActivity extends AppCompatActivity {

    myDbAdapter helper;
    ArrayList<String> aux = new ArrayList<>();
    ArrayList<String> allGames = new ArrayList<>();
    ArrayList<String> valueUser = new ArrayList<>();
    ArrayList<String> showGames = new ArrayList<>();
    String nameGame;
    Button b_confirm, b_cancel;
    ListView changes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DB
        helper = new myDbAdapter(this);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Set view
        setContentView(R.layout.activity_change_games);

        // We get the values for populating the List View
        allGames = helper.getAllGames();
        // Remove '_' from special games
        for (int index = 0; index < allGames.size(); index++) {
            nameGame = allGames.get(index);
            if (nameGame.contains("_")) {
                nameGame = nameGame.replace("_", " ");
            }
            showGames.add(nameGame);
        }
        aux = helper.getGamesByUser(SaveSharedPreferences.getUsername(getApplicationContext()));
        changes = findViewById(R.id.listGames);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_checked, showGames);

        changes.setAdapter(itemAdapter);
        changes.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        for (int index = 0; index < allGames.size(); index++) {
            if (aux.contains(showGames.get(index))) {
                changes.setItemChecked(index, true);
            }
        }

        // If user press confirm changes...
        b_confirm = findViewById(R.id.add_changes);
        b_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseBooleanArray sparseBooleanArray = changes.getCheckedItemPositions();
                for (int i = 0; i < allGames.size(); i++) {
                    if (sparseBooleanArray.get(i)) {
                        valueUser.add("YES");
                    } else {
                        valueUser.add("NO");
                    }
                }

                helper.setUserGames(SaveSharedPreferences.getUsername(getApplicationContext()), valueUser);
                Intent home = new Intent(ChangeGamesActivity.this, HomeActivity.class);
                home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(home);
                finish();
            }
        });

        // If user press cancel changes...
        b_cancel = findViewById(R.id.cancel_changes);
        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent account = new Intent(ChangeGamesActivity.this, AccountActivity.class);
                account.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                account.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(account);
                finish();
            }
        });


    }
}