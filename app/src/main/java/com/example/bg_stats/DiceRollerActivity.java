package com.example.bg_stats;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class DiceRollerActivity extends AppCompatActivity {

    private TextView diceCount;
    private Button plusNumber;
    private Button lessNumber;

    private Button button_dice_roll;

    private RadioGroup radioGroup;
    private RadioButton dice6Sided;
    private RadioButton dice12Sided;
    private RadioButton dice20Sided;

    private ListView dices;
    private ListAdapter listAdapter;

    private Random rng = new Random();

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

        //RadioButtons
        dice6Sided = (RadioButton) findViewById(R.id.dice6Sided);
        dice12Sided = (RadioButton) findViewById(R.id.dice12Sided);
        dice20Sided = (RadioButton) findViewById(R.id.dice20Sided);

        plusNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) diceCount.getText());
                if (number < 20) {
                    diceCount.setText(Integer.toString((number + 1)));
                }
            }
        });

        lessNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) diceCount.getText());
                if (number > 1) {
                    diceCount.setText(Integer.toString((number - 1)));
                }
            }
        });


        //List of dices
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
                switch (index) {
                    case 0:
                        dice6Sided.setButtonDrawable(R.drawable.d6_50px_pressed);
                        dice12Sided.setButtonDrawable(R.drawable.d12_50px);
                        dice20Sided.setButtonDrawable(R.drawable.d20_50px);
                        break;
                    case 1:
                        dice6Sided.setButtonDrawable(R.drawable.d6_50px);
                        dice12Sided.setButtonDrawable(R.drawable.d12_50px_pressed);
                        dice20Sided.setButtonDrawable(R.drawable.d20_50px);
                        break;
                    case 2:
                        dice6Sided.setButtonDrawable(R.drawable.d6_50px);
                        dice12Sided.setButtonDrawable(R.drawable.d12_50px);
                        dice20Sided.setButtonDrawable(R.drawable.d20_50px_pressed);
                        break;
                    default:
                        break;
                }
            }
        });

        button_dice_roll = (Button) findViewById(R.id.button_dice_roll);

        button_dice_roll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
                dices = (ListView) findViewById(R.id.dices);
                listAdapter = new ListAdapter(getArrayItems(index, Integer.parseInt((String) diceCount.getText())), DiceRollerActivity.this);
                dices.setAdapter(listAdapter);
            }
        });

    }

    private ArrayList<ListEntity> getArrayItems(int index, Integer diceCount) {
        ArrayList<ListEntity> listItems = new ArrayList<>();

        for (int i = 0; i < diceCount; i++) {
            if (index == 0) {
                int randomNumber = rng.nextInt(6) + 1;
                switch (randomNumber) {
                    case 1:
                        listItems.add(new ListEntity(R.drawable.d6_1));
                        break;
                    case 2:
                        listItems.add(new ListEntity(R.drawable.d6_2));
                        break;
                    case 3:
                        listItems.add(new ListEntity(R.drawable.d6_3));
                        break;
                    case 4:
                        listItems.add(new ListEntity(R.drawable.d6_4));
                        break;
                    case 5:
                        listItems.add(new ListEntity(R.drawable.d6_5));
                        break;
                    case 6:
                        listItems.add(new ListEntity(R.drawable.d6_6));
                        break;
                    default:
                        break;
                }
            } else if (index == 1) {
                int randomNumber = rng.nextInt(12) + 1;
                switch (randomNumber) {
                    case 1:
                        listItems.add(new ListEntity(R.drawable.d12_1));
                        break;
                    case 2:
                        listItems.add(new ListEntity(R.drawable.d12_2));
                        break;
                    case 3:
                        listItems.add(new ListEntity(R.drawable.d12_3));
                        break;
                    case 4:
                        listItems.add(new ListEntity(R.drawable.d12_4));
                        break;
                    case 5:
                        listItems.add(new ListEntity(R.drawable.d12_5));
                        break;
                    case 6:
                        listItems.add(new ListEntity(R.drawable.d12_6));
                        break;
                    case 7:
                        listItems.add(new ListEntity(R.drawable.d12_7));
                        break;
                    case 8:
                        listItems.add(new ListEntity(R.drawable.d12_8));
                        break;
                    case 9:
                        listItems.add(new ListEntity(R.drawable.d12_9));
                        break;
                    case 10:
                        listItems.add(new ListEntity(R.drawable.d12_10));
                        break;
                    case 11:
                        listItems.add(new ListEntity(R.drawable.d12_11));
                        break;
                    case 12:
                        listItems.add(new ListEntity(R.drawable.d12_12));
                        break;
                    default:
                        break;
                }
            } else if (index == 2) {
                int randomNumber = rng.nextInt(20) + 1;
                switch (randomNumber) {
                    case 1:
                        listItems.add(new ListEntity(R.drawable.d20_1));
                        break;
                    case 2:
                        listItems.add(new ListEntity(R.drawable.d20_2));
                        break;
                    case 3:
                        listItems.add(new ListEntity(R.drawable.d20_3));
                        break;
                    case 4:
                        listItems.add(new ListEntity(R.drawable.d20_4));
                        break;
                    case 5:
                        listItems.add(new ListEntity(R.drawable.d20_5));
                        break;
                    case 6:
                        listItems.add(new ListEntity(R.drawable.d20_6));
                        break;
                    case 7:
                        listItems.add(new ListEntity(R.drawable.d20_7));
                        break;
                    case 8:
                        listItems.add(new ListEntity(R.drawable.d20_8));
                        break;
                    case 9:
                        listItems.add(new ListEntity(R.drawable.d20_9));
                        break;
                    case 10:
                        listItems.add(new ListEntity(R.drawable.d20_10));
                        break;
                    case 11:
                        listItems.add(new ListEntity(R.drawable.d20_11));
                        break;
                    case 12:
                        listItems.add(new ListEntity(R.drawable.d20_12));
                        break;
                    case 13:
                        listItems.add(new ListEntity(R.drawable.d20_13));
                        break;
                    case 14:
                        listItems.add(new ListEntity(R.drawable.d20_14));
                        break;
                    case 15:
                        listItems.add(new ListEntity(R.drawable.d20_15));
                        break;
                    case 16:
                        listItems.add(new ListEntity(R.drawable.d20_16));
                        break;
                    case 17:
                        listItems.add(new ListEntity(R.drawable.d20_17));
                        break;
                    case 18:
                        listItems.add(new ListEntity(R.drawable.d20_18));
                        break;
                    case 19:
                        listItems.add(new ListEntity(R.drawable.d20_19));
                        break;
                    case 20:
                        listItems.add(new ListEntity(R.drawable.d20_20));
                        break;
                    default:
                        break;
                }
            }
        }
        return listItems;
    }
}
