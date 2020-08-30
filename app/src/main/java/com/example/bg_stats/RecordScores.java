package com.example.bg_stats;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecordScores extends AppCompatActivity {

    private EditText number_players_rs;
    private RecyclerView list_players_rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Set content view
        setContentView(R.layout.activity_record_scores);

        List<ActualPlayer> listPlayers = new ArrayList<>();
        ActualPlayer new_player = new ActualPlayer("", 0);
        listPlayers.add(new_player);

        number_players_rs = findViewById(R.id.number_players_rs);
        number_players_rs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                listPlayers.clear();
                if (!number_players_rs.getText().toString().equals("")) {
                    Integer num_players = Integer.valueOf(number_players_rs.getText().toString());
                    for (int i = 0; i < num_players; i++) {
                        ActualPlayer new_player = new ActualPlayer("", 0);
                        listPlayers.add(new_player);
                    }
                }
            }
        });

        list_players_rs = findViewById(R.id.list_players_rs);
        new RecyclerView_Config().setConfigPlayers(list_players_rs, RecordScores.this, listPlayers);

    }
}