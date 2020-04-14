package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    Boolean res;
    Button b1;
    EditText et1, et2, et3;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DB
        helper = new myDbAdapter(this);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Show sign up view
        setContentView(R.layout.activity_signup);

        // Username and password introduced by user
        b1 =  findViewById(R.id.b_sign_up);
        et1 = findViewById(R.id.sign_up_Username);
        et2 = findViewById(R.id.sign_up_Password);

        // When user clicks 'Done' button...
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et1.getText().toString();
                String password = et2.getText().toString();
                // Checks
                if (username.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Write an username",Toast.LENGTH_SHORT).show();
                } else if (username.length() < 5) {
                    Toast.makeText(getApplicationContext(),
                            "'" + username + "' is not valid. Username need to be 5 characters long",Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Write a password",Toast.LENGTH_SHORT).show();
                } else if (password.length() < 5) {
                    Toast.makeText(getApplicationContext(),
                            "'" + password + "' is not valid. Password need to be 5 characters long",Toast.LENGTH_SHORT).show();
                } else {
                    res = helper.insertNewUser(username, password);
                    if (res) {
                        Toast.makeText(getApplicationContext(),
                                "'" + username + "' User registered successfully", Toast.LENGTH_SHORT).show();
                        Intent login = new Intent(SignUpActivity.this, MainActivity.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(login);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "'" + username + "' Username already exist ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}