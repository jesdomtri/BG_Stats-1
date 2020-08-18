package com.example.bg_stats;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText et1, et2;
    TextView vt1;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

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
                    String email = et1.getText().toString();
                    String password = et2.getText().toString();

                    try {
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("Login", "signInWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Toast.makeText(MainActivity.this, "Successful login.",
                                                    Toast.LENGTH_SHORT).show();
                                            // Redirect to Home View
                                            Intent home = new Intent(MainActivity.this, HomeActivity.class);
                                            home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(home);
                                            // End login activity
                                            finish();
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w("Login", "signInWithEmail:failure", task.getException());
                                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
