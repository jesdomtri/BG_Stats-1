package com.example.bg_stats;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText et1, et2;
    TextView vt1, forgot_password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SaveSharedPreferences.getLoggedStatus(MainActivity.this) == true) {
            // Redirect to Home View
            Intent home = new Intent(MainActivity.this, HomeActivity.class);
            home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(home);
            // End login activity
            finish();
        }

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
            forgot_password = findViewById(R.id.forgot_your_password_text);

            vt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sign_up = new Intent(MainActivity.this, SignUpActivity.class);
                    startActivity(sign_up);
                }
            });

            forgot_password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view = inflater.inflate(R.layout.dialog_password, null);
                    final EditText email = view.findViewById(R.id.input_email_change_password);
                    String finalEmail = email.getText().toString();

                    new AlertDialog.Builder(MainActivity.this)
                            .setView(view)
                            .setPositiveButton(R.string.add_changes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    if (finalEmail.equals("")) {
                                        Toast.makeText(MainActivity.this, "Email is empty.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        mAuth.sendPasswordResetEmail(finalEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(MainActivity.this, "Email sent.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }
                            })
                            .setNegativeButton(R.string.cancel_changes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            })
                            .show();

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
                                            SaveSharedPreferences.setLoggedIn(MainActivity.this, true);
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
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
