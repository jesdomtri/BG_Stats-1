package com.example.bg_stats;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class AccountActivity extends AppCompatActivity {

    private TextView email_acc, emailUser_acc, password_acc, passwordUser_acc, uid_acc, uidUser_acc, date_acc, dateUser_acc;

    private Button buttonPassword_acc;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

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
                    mAuth.signOut();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        String userID = mAuth.getCurrentUser().getUid();
        String email = mAuth.getCurrentUser().getEmail();
        String password = "**********";
        long stamp = mAuth.getCurrentUser().getMetadata().getCreationTimestamp();
        Date date = new Date(stamp);
//        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
//        String strDate = dateFormat.format(date);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Set view
        setContentView(R.layout.activity_account);

        // Handle the bottom menu clicks
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(1).setChecked(true);

        emailUser_acc = findViewById(R.id.emailUser_acc);
        emailUser_acc.setText(email);

        passwordUser_acc = findViewById(R.id.passwordUser_acc);
        passwordUser_acc.setText(password);

        uidUser_acc = findViewById(R.id.uidUser_acc);
        uidUser_acc.setText(userID);

        dateUser_acc = findViewById(R.id.dateUser_acc);
        dateUser_acc.setText(date.toString());

        buttonPassword_acc = findViewById(R.id.buttonPassword_acc);
        buttonPassword_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(AccountActivity.this, "Email sent.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}
