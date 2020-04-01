package com.example.bg_stats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DiceRollerActivity extends AppCompatActivity {

    public TextView diceCount;
    public Button plusNumber;
    public Button lessNumber;

    public ListView dices;
    public RadioGroup radioGroup;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Set view
        setContentView(R.layout.activity_dice);

        //Plus and less number
        diceCount = (TextView) findViewById(R.id.diceCount);
        plusNumber = (Button) findViewById(R.id.plusNumber);
        lessNumber = (Button) findViewById(R.id.lessNumber);

        plusNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) diceCount.getText());
                if (number < 20){
                    diceCount.setText(Integer.toString((number + 1)));
                }
            }
        });

        lessNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) diceCount.getText());
                if (number > 0){
                    diceCount.setText(Integer.toString((number - 1)));
                }
            }
        });


        RadioGroup rGroup = findViewById(R.id.radioGroup);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = group.findViewById(checkedId);
            }
        });
    }


}
