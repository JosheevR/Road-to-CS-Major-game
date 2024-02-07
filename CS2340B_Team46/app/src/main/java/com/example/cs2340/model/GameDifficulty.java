package com.example.cs2340.model;

public enum GameDifficulty {
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard");

    private final String name;

    private GameDifficulty(String s) {
        name = s;
    }

    public String toString() {
        return name;
    }
}
