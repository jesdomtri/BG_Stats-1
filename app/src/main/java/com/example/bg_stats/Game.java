package com.example.bg_stats;

import java.util.HashMap;

public class Game {

    private Integer gameId;
    private String name;

    public Game() {
    }

    public Game(Integer gameId, String name) {
        this.gameId = gameId;
        this.name = name;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
