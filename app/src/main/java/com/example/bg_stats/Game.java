package com.example.bg_stats;

public class Game implements Comparable<Game> {

    private String name;

    public Game() {
    }

    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Game o) {
        return this.name.compareTo(o.name);
    }
}
