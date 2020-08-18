package com.example.bg_stats;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.Change;

import java.util.List;

public class ChangeGamesActivity extends AppCompatActivity {

    private ValueEventListener eventListener;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String userID = mAuth.getCurrentUser().getUid();
//        mDatabase.child("Usuarios").child(userID).child("Juegos").setValue("Aaaaaa");

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Show home view
        setContentView(R.layout.activity_change_games);

        mRecyclerView = findViewById(R.id.listGames);
        new FirebaseDatabaseHelper().readGames(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Game> games, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, ChangeGamesActivity.this, games, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}