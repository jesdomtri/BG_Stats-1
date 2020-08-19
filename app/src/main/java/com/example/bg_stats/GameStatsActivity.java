package com.example.bg_stats;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class GameStatsActivity extends AppCompatActivity {

    myDbAdapter helper;
    TextView oTextView, oTitleStat;
    ListView oListView;
    LineChartView lineChartView;
    ArrayAdapter<String> StableAdapter;
    List<String> StatsOptions = Arrays.asList("Games played by month", "Games won by month", "Games lost by month");
    String username;
    String SelectedGame;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DB
        helper = new myDbAdapter(this);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Show home view
        setContentView(R.layout.activity_gamestats);

        // Set title text
        username = "";
        SelectedGame = getIntent().getStringExtra("Game_name");
        oTextView = findViewById(R.id.game_stats);
        String string_title = SelectedGame + " Stats";
        oTextView.setText(string_title);

        // Initialize the list with the different stats
        oTitleStat = findViewById(R.id.stats_title);
        oListView = findViewById(R.id.stats_list);
        StableAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, StatsOptions);
        oListView.setAdapter(StableAdapter);


        oListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        oTitleStat.setText(StatsOptions.get(position));
                        lineChartView = findViewById(R.id.chart);

                        String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
                        int[] yAxisData = helper.getFilteredGamesByMonthsAndUserAndGame(username, SelectedGame);

                        List yAxisValues = new ArrayList();
                        List axisValues = new ArrayList();

                        Line line = new Line(yAxisValues);

                        for(int i = 0; i < axisData.length; i++){
                            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
                        }

                        for (int i = 0; i < yAxisData.length; i++){
                            yAxisValues.add(new PointValue(i, yAxisData[i]));
                        }

                        List lines = new ArrayList();
                        lines.add(line);

                        LineChartData data = new LineChartData();
                        data.setLines(lines);

                        Axis axis = new Axis();
                        axis.setValues(axisValues);
                        data.setAxisXBottom(axis);

                        Axis yAxis = new Axis();
                        data.setAxisYLeft(yAxis);

                        axis.setTextColor(Color.parseColor("#03A9F4"));

                        lineChartView.setLineChartData(data);
                        lineChartView.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        oTitleStat.setText(StatsOptions.get(position));
                        lineChartView = findViewById(R.id.chart);

                        String[] axisData1 = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
                        int[] yAxisData1 = helper.getFilteredWinsByMonthsAndUserAndGame(username, SelectedGame);

                        List yAxisValues1 = new ArrayList();
                        List axisValues1 = new ArrayList();

                        Line line1 = new Line(yAxisValues1);

                        for(int i = 0; i < axisData1.length; i++){
                            axisValues1.add(i, new AxisValue(i).setLabel(axisData1[i]));
                        }

                        for (int i = 0; i < yAxisData1.length; i++){
                            yAxisValues1.add(new PointValue(i, yAxisData1[i]));
                        }

                        List lines1 = new ArrayList();
                        lines1.add(line1);

                        LineChartData data1 = new LineChartData();
                        data1.setLines(lines1);

                        Axis axis1 = new Axis();
                        axis1.setValues(axisValues1);
                        data1.setAxisXBottom(axis1);

                        Axis yAxis1 = new Axis();
                        data1.setAxisYLeft(yAxis1);

                        axis1.setTextColor(Color.parseColor("#03A9F4"));

                        lineChartView.setLineChartData(data1);
                        lineChartView.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        oTitleStat.setText(StatsOptions.get(position));
                        lineChartView = findViewById(R.id.chart);

                        String[] axisData2 = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
                        int[] yAxisData2 = helper.getFilteredLostByMonthsAndUserAndGame(username, SelectedGame);

                        List yAxisValues2 = new ArrayList();
                        List axisValues2 = new ArrayList();

                        Line line2 = new Line(yAxisValues2);

                        for(int i = 0; i < axisData2.length; i++){
                            axisValues2.add(i, new AxisValue(i).setLabel(axisData2[i]));
                        }

                        for (int i = 0; i < yAxisData2.length; i++){
                            yAxisValues2.add(new PointValue(i, yAxisData2[i]));
                        }

                        List lines2 = new ArrayList();
                        lines2.add(line2);

                        LineChartData data2 = new LineChartData();
                        data2.setLines(lines2);

                        Axis axis2 = new Axis();
                        axis2.setValues(axisValues2);
                        data2.setAxisXBottom(axis2);

                        Axis yAxis2 = new Axis();
                        data2.setAxisYLeft(yAxis2);

                        axis2.setTextColor(Color.parseColor("#03A9F4"));

                        lineChartView.setLineChartData(data2);
                        lineChartView.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

    }
}
