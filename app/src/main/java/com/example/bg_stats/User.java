package com.example.bg_stats;

import java.util.HashMap;

public class User {
    private HashMap games;

    public User() {
    }

    public User(HashMap games) {
        this.games = games;
    }

    public HashMap getGames() {
        return games;
    }

    public void setGames(HashMap games) {
        this.games = games;
    }
}
