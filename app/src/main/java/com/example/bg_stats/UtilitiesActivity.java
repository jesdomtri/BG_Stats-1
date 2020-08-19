package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class UtilitiesActivity extends AppCompatActivity {

    TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Set view
        setContentView(R.layout.activity_utilities);

        // Handle the bottom menu clicks
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(2).setChecked(true);
    }

    public void onDiceRollClick(View view) {
        Intent diceRoll = new Intent(UtilitiesActivity.this, DiceRollerActivity.class);
        startActivity(diceRoll);
    }


    public void onMagicArenaClick(View view) {
        Intent magicArena = new Intent(UtilitiesActivity.this, MagicArenaActivity.class);
        startActivity(magicArena);
    }

    public void onChronometerClick(View view) {
        Intent chronometer = new Intent(UtilitiesActivity.this, ChronometerActivity.class);
        startActivity(chronometer);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(UtilitiesActivity.this, HomeActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_account:
                    Intent account = new Intent(UtilitiesActivity.this, AccountActivity.class);
                    startActivity(account);
                    break;
                case R.id.navigation_logout:
                    SaveSharedPreferences.setLoggedIn(getApplicationContext(), false);

                    Intent login = new Intent(UtilitiesActivity.this, MainActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(login);
                    finish();
                    break;
                case R.id.navigation_utility:
                    break;
            }
            return false;
        }
    };
}
