package com.example.bg_stats;

import java.util.List;

public class User {
    public String email, id;
    public List<Game> games;

    public User() {
    }

    public User(String email, String id, List<Game> games) {
        this.email = email;
        this.id = id;
        this.games = games;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
