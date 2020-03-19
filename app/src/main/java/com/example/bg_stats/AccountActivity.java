package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {

    myDbAdapter helper;
    TextView oTextView, changeUsername, changePassword, changeQuestion, changeGames, mTextMessage, nameField;
    ArrayList<String> allGames = new ArrayList<>();
    String valueUser, gameName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DB
        helper = new myDbAdapter(this);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Set view
        setContentView(R.layout.activity_account);

        // Set account title
        oTextView = findViewById(R.id.account_title);
        oTextView.setText(SaveSharedPreferences.getUsername(getApplicationContext()) + " Account");

        // If user click change username...
        changeUsername = findViewById(R.id.change_username);
        changeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // PENDIENTE - Lanzar un PopUp donde podrá elegir un nuevo username, confirmando con la contraseña
            }
        });

        // If user click change password...
        changePassword = findViewById(R.id.change_password);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // PENDIENTE - Lanzar un PopUp donde podrá elegir una nueva contraseña, confirmando la anterior.
            }
        });

        // If user click change question...
        changeQuestion = findViewById(R.id.change_question);
        changeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // PENDIENTE - Mostrar la anterior junto a un EditText donde el usuario podrá elegir una nueva
                // respuesta para la pregunta estándar (o el conjunto, según lo implemente)
            }
        });


        // If user click change games...
        changeGames = findViewById(R.id.change_games);
        changeGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeGames = new Intent(AccountActivity.this, ChangeGamesActivity.class);
                startActivity(changeGames);
            }
        });

        // Handle the bottom menu clicks
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(AccountActivity.this, HomeActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_account:
                    break;
                case R.id.navigation_logout:
                    SaveSharedPreferences.setLoggedIn(getApplicationContext(), false);
                    SaveSharedPreferences.setUsername(getApplicationContext(), "");
                    Intent login = new Intent(AccountActivity.this, MainActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(login);
                    finish();
                    break;
                case R.id.navigation_utility:
                    Intent utility = new Intent(AccountActivity.this, UtilitiesActivity.class);
                    startActivity(utility);
                    break;
            }
            return false;
        }
    };
}
