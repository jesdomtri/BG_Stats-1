package com.example.bg_stats;

import java.util.ArrayList;

public class GamePreview {

    private String gameName;
    private String ID_1;
    private String ID_2;
    private String ID_3;
    private String ID_4;
    private String ID_5;
    private String ID_6;
    private String ID_7;
    private String ID_8;
    private String Score_1;
    private String Score_2;
    private String Score_3;
    private String Score_4;
    private String Score_5;
    private String Score_6;
    private String Score_7;
    private String Score_8;
    private String Commentary;
    private String Date;

    public GamePreview(ArrayList<String> parameters) {
        this.gameName = parameters.get(0);
        this.ID_1 = parameters.get(1);
        this.ID_2 = parameters.get(2);
        this.ID_3 = parameters.get(3);
        this.ID_4 = parameters.get(4);
        this.ID_5 = parameters.get(5);
        this.ID_6 = parameters.get(6);
        this.ID_7 = parameters.get(7);
        this.ID_8 = parameters.get(8);
        Score_1 = parameters.get(9);
        Score_2 = parameters.get(10);
        Score_3 = parameters.get(11);
        Score_4 = parameters.get(12);
        Score_5 = parameters.get(13);
        Score_6 = parameters.get(14);
        Score_7 = parameters.get(15);
        Score_8 = parameters.get(16);
        Commentary = parameters.get(17);
        Date = parameters.get(18);
    }

    public String getWinner () {
        return ID_1;
    }

    public String getPosition(String username) {
        Integer position;

        if (username.equals(ID_1)) {
            position = 1;
        } else if (username.equals(ID_2)) {
            position = 2;
        } else if (username.equals(ID_3)) {
            position = 3;
        } else if (username.equals(ID_4)) {
            position = 4;
        } else if (username.equals(ID_5)) {
            position = 5;
        } else if (username.equals(ID_6)) {
            position = 6;
        } else if (username.equals(ID_7)) {
            position = 7;
        } else if (username.equals(ID_8)) {
            position = 8;
        } else {
            position = 0;
        }

        return position.toString();
    }

}
