package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class SignUpActivity extends AppCompatActivity {
    Button b1;
    EditText et1, et2;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Show sign up view
        setContentView(R.layout.activity_signup);

        // Username and password introduced by user
        b1 = findViewById(R.id.b_sign_up);
        et1 = findViewById(R.id.sign_up_Username);
        et2 = findViewById(R.id.sign_up_Password);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et1.getText().toString();
                String password = et2.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("SignUp", "createUserWithEmail:success");

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    User user1 = new User(user.getEmail(), user.getUid(), new ArrayList<Game>());
                                    FirebaseDatabase.getInstance().getReference("/").child("Users").child(user.getUid()).setValue(user1);

                                    Toast.makeText(SignUpActivity.this, "User registered successfully.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent login = new Intent(SignUpActivity.this, MainActivity.class);
                                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(login);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("SignUp", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpActivity.this, "Sign up failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}