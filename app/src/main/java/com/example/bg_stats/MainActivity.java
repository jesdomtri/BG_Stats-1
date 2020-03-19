package com.example.bg_stats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText et1, et2;
    TextView vt1;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DB
        helper = new myDbAdapter(this);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // If user is already logged then...
        if (SaveSharedPreferences.getLoggedStatus(getApplicationContext())) {
            Intent home = new Intent(getApplicationContext(), HomeActivity.class);
            home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(home);
            //End login activity
            finish();
        } else {
            // Show login view
            setContentView(R.layout.activity_login);

            // Username and password introduced by user
            b1 = findViewById(R.id.b_login);
            et1 = findViewById(R.id.input_username);
            et2 = findViewById(R.id.input_password);
            vt1 = findViewById(R.id.login_register);


            vt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sign_up = new Intent(MainActivity.this, SignUpActivity.class);
                    startActivity(sign_up);
                }
            });

            // Login process
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //If username exists then... otherwise wrong credential
                    if (helper.usernameCheck(et1.getText().toString())) {
                        if (helper.passwordCheck(et1.getText().toString(), et2.getText().toString())) {
                            Toast.makeText(getApplicationContext(),
                                    "Login Successful ", Toast.LENGTH_SHORT).show();
                            // Redirect to Home View
                            Intent home = new Intent(MainActivity.this, HomeActivity.class);
                            home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            SaveSharedPreferences.setLoggedIn(getApplicationContext(), true);
                            SaveSharedPreferences.setUsername(getApplicationContext(), et1.getText().toString());
                            startActivity(home);
                            // End login activity
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Incorrect password for username '" + et1.getText().toString() + "'", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "'" + et1.getText().toString() + "' username does not exist ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
