package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GameListActivity extends AppCompatActivity {

    myDbAdapter helper;
    TextView mTextMessage, oTitleView, mDefaultMessage;
    ListView oGameList;
    ArrayList<GamePreview> listOfRows;
    ArrayAdapter<GamePreview> itemAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        String selectedGame = getIntent().getStringExtra("Game_name");
        String gameTitle = selectedGame + " game list";
        oTitleView = findViewById(R.id.gamelist_title);
        oTitleView.setText(gameTitle);

        // Set adapter
        oGameList = findViewById(R.id.gameList);
        String username = SaveSharedPreferences.getUsername(getApplicationContext());
        listOfRows = helper.getGamesPlayedByUserAndGame(username, selectedGame);
        itemAdapter = new GameAdapter(this,R.layout.game_item, listOfRows);
        oGameList.setAdapter(itemAdapter);

        if (listOfRows.isEmpty()) {
            mDefaultMessage = findViewById(R.id.defaultText2);
            mDefaultMessage.setVisibility(View.VISIBLE);
        }
    }

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
                    SaveSharedPreferences.setUsername(getApplicationContext(), "");
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
}
