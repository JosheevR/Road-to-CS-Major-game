package com.example.cs2340.model;

import java.io.Serializable;

public class GameState implements Serializable {
    private int attempt;
    private GameDuration elapsedTime;
    private int score;
    public GameState(int attempt, int score) {
        this.attempt = attempt;
        this.elapsedTime = new GameDuration();
        this.score = score;
    }
    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public GameDuration getElapsedTime() {
        return elapsedTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
