package com.example.bg_stats;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceUserGames;
    private DatabaseReference mReferenceUsers;
    private DatabaseReference mReferenceGames;
    private DatabaseReference mReferenceMatches;
    private List<Game> allGames = new ArrayList<>();
    private List<Game> userGames = new ArrayList<>();
    private List<User> allUsers = new ArrayList<>();

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid();
        mReferenceUserGames = mDatabase.getReference("Users/" + userID + "/Games");
        mReferenceGames = mDatabase.getReference("Games");
        mReferenceUsers = mDatabase.getReference("Users/");
    }

    public void readGames(final DataStatus dataStatus) {

        readUserGames(dataStatus);

        mReferenceGames.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> names = new ArrayList<>();
                for (Game game : userGames) {
                    names.add(game.getName());
                }
                allGames.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Game game = keyNode.getValue(Game.class);
                    if (!names.contains(game.getName())) {
                        allGames.add(game);
                    }
                }
                dataStatus.DataIsLoaded(allGames, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addGame(Game game, final DataStatus dataStatus) {
        String key = mReferenceUserGames.push().getKey();
        mReferenceUserGames.child(key).setValue(game).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }

    public void deleteGame(String key, final DataStatus dataStatus) {
        mReferenceUserGames.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }

    public void readMatches(FirebaseAuth mAuth, String titleGame, final DataStatus dataStatus) {
        mReferenceMatches = mDatabase.getReference("Matches/" + mAuth.getCurrentUser().getUid() + "/" + titleGame);
        mReferenceMatches.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void readUserGames(final DataStatus dataStatus) {
        mReferenceUserGames.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userGames.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Game game = keyNode.getValue(Game.class);
                    userGames.add(game);
                }
                dataStatus.DataIsLoaded(userGames, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void readUsers(final DataStatus dataStatus) {
        mReferenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allUsers.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    User user = keyNode.getValue(User.class);
                    allUsers.add(user);
                }
                dataStatus.UsersAreLoaded(allUsers, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public interface DataStatus {
        void DataIsLoaded(List<Game> games, List<String> keys);

        void DataIsInserted();

        void DataIsUpdated();

        void DataIsDeleted();

        void UsersAreLoaded(List<User> allUsers, List<String> keys);
    }

}
