package com.example.bg_stats;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MagicArenaActivity extends AppCompatActivity {

    /////////////////////////////////////// Reset Button ///////////////////////////////////////

    public ImageView reset;

    /////////////////////////////////////// Player 1 ///////////////////////////////////////

    public TextView count_health_1, count_poison_1, count_commander_1;

    public Button btn_health_1_n1, btn_health_1_n5, btn_health_1_y1, btn_health_1_y5;

    public Button btn_poison_1_n1, btn_poison_1_n5, btn_poison_1_y1, btn_poison_1_y5;

    public Button btn_commander_1_n1, btn_commander_1_n5, btn_commander_1_y1, btn_commander_1_y5;

    /////////////////////////////////////// Player 2 ///////////////////////////////////////

    public TextView count_health_2, count_poison_2, count_commander_2;

    public Button btn_health_2_n1, btn_health_2_n5, btn_health_2_y1, btn_health_2_y5;

    public Button btn_poison_2_n1, btn_poison_2_n5, btn_poison_2_y1, btn_poison_2_y5;

    public Button btn_commander_2_n1, btn_commander_2_n5, btn_commander_2_y1, btn_commander_2_y5;

    /////////////////////////////////////// Player 3 ///////////////////////////////////////

    public TextView count_health_3, count_poison_3, count_commander_3;

    public Button btn_health_3_n1, btn_health_3_n5, btn_health_3_y1, btn_health_3_y5;

    public Button btn_poison_3_n1, btn_poison_3_n5, btn_poison_3_y1, btn_poison_3_y5;

    public Button btn_commander_3_n1, btn_commander_3_n5, btn_commander_3_y1, btn_commander_3_y5;

    /////////////////////////////////////// Player 4 ///////////////////////////////////////

    public TextView count_health_4, count_poison_4, count_commander_4;

    public Button btn_health_4_n1, btn_health_4_n5, btn_health_4_y1, btn_health_4_y5;

    public Button btn_poison_4_n1, btn_poison_4_n5, btn_poison_4_y1, btn_poison_4_y5;

    public Button btn_commander_4_n1, btn_commander_4_n5, btn_commander_4_y1, btn_commander_4_y5;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Set view
        setContentView(R.layout.activity_magic);

        /////////////////////////////////////// Reset Button ///////////////////////////////////////

        reset = (ImageView) findViewById(R.id.reset);

        /////////////////////////////////////// Player 1 ///////////////////////////////////////

        count_health_1 = (TextView) findViewById(R.id.count_health_1);
        btn_health_1_n1 = (Button) findViewById(R.id.btn_health_1_n1);
        btn_health_1_n5 = (Button) findViewById(R.id.btn_health_1_n5);
        btn_health_1_y1 = (Button) findViewById(R.id.btn_health_1_y1);
        btn_health_1_y5 = (Button) findViewById(R.id.btn_health_1_y5);

        count_poison_1 = (TextView) findViewById(R.id.count_poison_1);
        btn_poison_1_n1 = (Button) findViewById(R.id.btn_poison_1_n1);
        btn_poison_1_n5 = (Button) findViewById(R.id.btn_poison_1_n5);
        btn_poison_1_y1 = (Button) findViewById(R.id.btn_poison_1_y1);
        btn_poison_1_y5 = (Button) findViewById(R.id.btn_poison_1_y5);

        count_commander_1 = (TextView) findViewById(R.id.count_commander_1);
        btn_commander_1_n1 = (Button) findViewById(R.id.btn_commander_1_n1);
        btn_commander_1_n5 = (Button) findViewById(R.id.btn_commander_1_n5);
        btn_commander_1_y1 = (Button) findViewById(R.id.btn_commander_1_y1);
        btn_commander_1_y5 = (Button) findViewById(R.id.btn_commander_1_y5);

        /////////////////////////////////////// Player 2 ///////////////////////////////////////

        count_health_2 = (TextView) findViewById(R.id.count_health_2);
        btn_health_2_n1 = (Button) findViewById(R.id.btn_health_2_n1);
        btn_health_2_n5 = (Button) findViewById(R.id.btn_health_2_n5);
        btn_health_2_y1 = (Button) findViewById(R.id.btn_health_2_y1);
        btn_health_2_y5 = (Button) findViewById(R.id.btn_health_2_y5);

        count_poison_2 = (TextView) findViewById(R.id.count_poison_2);
        btn_poison_2_n1 = (Button) findViewById(R.id.btn_poison_2_n1);
        btn_poison_2_n5 = (Button) findViewById(R.id.btn_poison_2_n5);
        btn_poison_2_y1 = (Button) findViewById(R.id.btn_poison_2_y1);
        btn_poison_2_y5 = (Button) findViewById(R.id.btn_poison_2_y5);

        count_commander_2 = (TextView) findViewById(R.id.count_commander_2);
        btn_commander_2_n1 = (Button) findViewById(R.id.btn_commander_2_n1);
        btn_commander_2_n5 = (Button) findViewById(R.id.btn_commander_2_n5);
        btn_commander_2_y1 = (Button) findViewById(R.id.btn_commander_2_y1);
        btn_commander_2_y5 = (Button) findViewById(R.id.btn_commander_2_y5);

        /////////////////////////////////////// Player 3 ///////////////////////////////////////

        count_health_3 = (TextView) findViewById(R.id.count_health_3);
        btn_health_3_n1 = (Button) findViewById(R.id.btn_health_3_n1);
        btn_health_3_n5 = (Button) findViewById(R.id.btn_health_3_n5);
        btn_health_3_y1 = (Button) findViewById(R.id.btn_health_3_y1);
        btn_health_3_y5 = (Button) findViewById(R.id.btn_health_3_y5);

        count_poison_3 = (TextView) findViewById(R.id.count_poison_3);
        btn_poison_3_n1 = (Button) findViewById(R.id.btn_poison_3_n1);
        btn_poison_3_n5 = (Button) findViewById(R.id.btn_poison_3_n5);
        btn_poison_3_y1 = (Button) findViewById(R.id.btn_poison_3_y1);
        btn_poison_3_y5 = (Button) findViewById(R.id.btn_poison_3_y5);

        count_commander_3 = (TextView) findViewById(R.id.count_commander_3);
        btn_commander_3_n1 = (Button) findViewById(R.id.btn_commander_3_n1);
        btn_commander_3_n5 = (Button) findViewById(R.id.btn_commander_3_n5);
        btn_commander_3_y1 = (Button) findViewById(R.id.btn_commander_3_y1);
        btn_commander_3_y5 = (Button) findViewById(R.id.btn_commander_3_y5);

        /////////////////////////////////////// Player 4 ///////////////////////////////////////

        count_health_4 = (TextView) findViewById(R.id.count_health_4);
        btn_health_4_n1 = (Button) findViewById(R.id.btn_health_4_n1);
        btn_health_4_n5 = (Button) findViewById(R.id.btn_health_4_n5);
        btn_health_4_y1 = (Button) findViewById(R.id.btn_health_4_y1);
        btn_health_4_y5 = (Button) findViewById(R.id.btn_health_4_y5);

        count_poison_4 = (TextView) findViewById(R.id.count_poison_4);
        btn_poison_4_n1 = (Button) findViewById(R.id.btn_poison_4_n1);
        btn_poison_4_n5 = (Button) findViewById(R.id.btn_poison_4_n5);
        btn_poison_4_y1 = (Button) findViewById(R.id.btn_poison_4_y1);
        btn_poison_4_y5 = (Button) findViewById(R.id.btn_poison_4_y5);

        count_commander_4 = (TextView) findViewById(R.id.count_commander_4);
        btn_commander_4_n1 = (Button) findViewById(R.id.btn_commander_4_n1);
        btn_commander_4_n5 = (Button) findViewById(R.id.btn_commander_4_n5);
        btn_commander_4_y1 = (Button) findViewById(R.id.btn_commander_4_y1);
        btn_commander_4_y5 = (Button) findViewById(R.id.btn_commander_4_y5);

        /////////////////////////////////////// Buttons ///////////////////////////////////////

        btn_health_1_n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_1.getText());
                count_health_1.setText(Integer.toString((number - 1)));
            }
        });

        btn_health_1_y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_1.getText());
                count_health_1.setText(Integer.toString((number + 1)));
            }
        });

        btn_health_1_n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_1.getText());
                count_health_1.setText(Integer.toString((number - 5)));
            }
        });

        btn_health_1_y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_1.getText());
                count_health_1.setText(Integer.toString((number + 5)));
            }
        });

        btn_poison_1_n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_1.getText());
                count_poison_1.setText(Integer.toString((number - 1)));
            }
        });

        btn_poison_1_y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_1.getText());
                count_poison_1.setText(Integer.toString((number + 1)));
            }
        });

        btn_poison_1_n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_1.getText());
                count_poison_1.setText(Integer.toString((number - 5)));
            }
        });

        btn_poison_1_y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_1.getText());
                count_poison_1.setText(Integer.toString((number + 5)));
            }
        });

        btn_commander_1_n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_1.getText());
                count_commander_1.setText(Integer.toString((number - 1)));
            }
        });

        btn_commander_1_y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_1.getText());
                count_commander_1.setText(Integer.toString((number + 1)));
            }
        });

        btn_commander_1_n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_1.getText());
                count_commander_1.setText(Integer.toString((number - 5)));
            }
        });

        btn_commander_1_y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_1.getText());
                count_commander_1.setText(Integer.toString((number + 5)));
            }
        });

        btn_health_2_n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_2.getText());
                count_health_2.setText(Integer.toString((number - 1)));
            }
        });

        btn_health_2_y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_2.getText());
                count_health_2.setText(Integer.toString((number + 1)));
            }
        });

        btn_health_2_n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_2.getText());
                count_health_2.setText(Integer.toString((number - 5)));
            }
        });

        btn_health_2_y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_2.getText());
                count_health_2.setText(Integer.toString((number + 5)));
            }
        });

        btn_poison_2_n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_2.getText());
                count_poison_2.setText(Integer.toString((number - 1)));
            }
        });

        btn_poison_2_y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_2.getText());
                count_poison_2.setText(Integer.toString((number + 1)));
            }
        });

        btn_poison_2_n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_2.getText());
                count_poison_2.setText(Integer.toString((number - 5)));
            }
        });

        btn_poison_2_y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_2.getText());
                count_poison_2.setText(Integer.toString((number + 5)));
            }
        });

        btn_commander_2_n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_2.getText());
                count_commander_2.setText(Integer.toString((number - 1)));
            }
        });

        btn_commander_2_y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_2.getText());
                count_commander_2.setText(Integer.toString((number + 1)));
            }
        });

        btn_commander_2_n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_2.getText());
                count_commander_2.setText(Integer.toString((number - 5)));
            }
        });

        btn_commander_2_y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_2.getText());
                count_commander_2.setText(Integer.toString((number + 5)));
            }
        });

        btn_health_3_n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_3.getText());
                count_health_3.setText(Integer.toString((number - 1)));
            }
        });

        btn_health_3_y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_3.getText());
                count_health_3.setText(Integer.toString((number + 1)));
            }
        });

        btn_health_3_n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_3.getText());
                count_health_3.setText(Integer.toString((number - 5)));
            }
        });

        btn_health_3_y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_3.getText());
                count_health_3.setText(Integer.toString((number + 5)));
            }
        });

        btn_poison_3_n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_3.getText());
                count_poison_3.setText(Integer.toString((number - 1)));
            }
        });

        btn_poison_3_y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_3.getText());
                count_poison_3.setText(Integer.toString((number + 1)));
            }
        });

        btn_poison_3_n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_3.getText());
                count_poison_3.setText(Integer.toString((number - 5)));
            }
        });

        btn_poison_3_y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_3.getText());
                count_poison_3.setText(Integer.toString((number + 5)));
            }
        });

        btn_commander_3_n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_3.getText());
                count_commander_3.setText(Integer.toString((number - 1)));
            }
        });

        btn_commander_3_y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_3.getText());
                count_commander_3.setText(Integer.toString((number + 1)));
            }
        });

        btn_commander_3_n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_3.getText());
                count_commander_3.setText(Integer.toString((number - 5)));
            }
        });

        btn_commander_3_y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_3.getText());
                count_commander_3.setText(Integer.toString((number + 5)));
            }
        });

        btn_health_4_n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_4.getText());
                count_health_4.setText(Integer.toString((number - 1)));
            }
        });

        btn_health_4_y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_4.getText());
                count_health_4.setText(Integer.toString((number + 1)));
            }
        });

        btn_health_4_n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_4.getText());
                count_health_4.setText(Integer.toString((number - 5)));
            }
        });

        btn_health_4_y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_health_4.getText());
                count_health_4.setText(Integer.toString((number + 5)));
            }
        });

        btn_poison_4_n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_4.getText());
                count_poison_4.setText(Integer.toString((number - 1)));
            }
        });

        btn_poison_4_y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_4.getText());
                count_poison_4.setText(Integer.toString((number + 1)));
            }
        });

        btn_poison_4_n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_4.getText());
                count_poison_4.setText(Integer.toString((number - 5)));
            }
        });

        btn_poison_4_y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_poison_4.getText());
                count_poison_4.setText(Integer.toString((number + 5)));
            }
        });

        btn_commander_4_n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_4.getText());
                count_commander_4.setText(Integer.toString((number - 1)));
            }
        });

        btn_commander_4_y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_4.getText());
                count_commander_4.setText(Integer.toString((number + 1)));
            }
        });

        btn_commander_4_n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_4.getText());
                count_commander_4.setText(Integer.toString((number - 5)));
            }
        });

        btn_commander_4_y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer number = Integer.parseInt((String) count_commander_4.getText());
                count_commander_4.setText(Integer.toString((number + 5)));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                count_health_1.setText("20");
                count_health_2.setText("20");
                count_health_3.setText("20");
                count_health_4.setText("20");

                count_poison_1.setText("0");
                count_poison_2.setText("0");
                count_poison_3.setText("0");
                count_poison_4.setText("0");

                count_commander_1.setText("0");
                count_commander_2.setText("0");
                count_commander_3.setText("0");
                count_commander_4.setText("0");
            }
        });

    }

}
