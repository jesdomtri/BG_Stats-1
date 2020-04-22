package com.example.bg_stats;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog_change_username, null);
                final EditText edit_username = (EditText) view.findViewById(R.id.input_new_username);

                new AlertDialog.Builder(AccountActivity.this)
                        .setTitle("Change username")
                        .setView(view)
                        .setPositiveButton(R.string.add_changes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                helper.changeUserName(SaveSharedPreferences.getUsername(getApplicationContext()), edit_username.getText().toString());
                                SaveSharedPreferences.setUsername(getApplicationContext(), edit_username.getText().toString());
                                Toast.makeText(getApplicationContext(),
                                        "Username changed", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(getIntent());
                            }
                        })
                        .setNegativeButton(R.string.cancel_changes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),
                                        "Username not changed", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        // If user click change password...
        changePassword = findViewById(R.id.change_password);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog_change_password, null);
                final EditText edit_password = (EditText) view.findViewById(R.id.input_new_password);

                new AlertDialog.Builder(AccountActivity.this)
                        .setTitle("Change password")
                        .setView(view)
                        .setPositiveButton(R.string.add_changes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                helper.changePassword(SaveSharedPreferences.getUsername(getApplicationContext()), edit_password.getText().toString());
                                Toast.makeText(getApplicationContext(),
                                        "Password changed", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(getIntent());
                            }
                        })
                        .setNegativeButton(R.string.cancel_changes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),
                                        "Password not changed", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
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
        navView.getMenu().getItem(1).setChecked(true);

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
