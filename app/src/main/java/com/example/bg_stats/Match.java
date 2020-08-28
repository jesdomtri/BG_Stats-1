package com.example.bg_stats;

public class Match {

    private String score;
    private Integer position;
    private Boolean winner;

    public Match() {
    }

    public Match(String score, Integer position, Boolean winner) {
        this.score = score;
        this.position = position;
        this.winner = winner;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }
}
