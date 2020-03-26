package com.example.bg_stats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DiceRollerActivity extends AppCompatActivity {

    TextView diceCount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Set view
        setContentView(R.layout.activity_dice);

        RadioGroup rGroup = findViewById(R.id.radioGroup);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = group.findViewById(checkedId);
            }
        });
    }

    public void onPlusNumberClick(View view) {
        diceCount = findViewById(R.id.diceCount);
        diceCount.setText(Integer.parseInt(diceCount.getText().toString()) + 1);
    }

    public void onLessNumberClick(View view) {
        diceCount = findViewById(R.id.diceCount);
        diceCount.setText(Integer.parseInt(diceCount.getText().toString()) - 1);
    }

}
