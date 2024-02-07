package com.example.cs2340.model;

import java.io.Serializable;

public class GameConfiguration implements Serializable {
    private String playerName;
    private GameDifficulty difficulty;
    private Sprite sprite;

    public GameConfiguration(String playerName, GameDifficulty difficulty, Sprite sprite) {
        this.playerName = playerName;
        this.difficulty = difficulty;
        this.sprite = sprite;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(GameDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
