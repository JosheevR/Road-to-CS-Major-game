package com.example.cs2340.model;

import java.util.Date;

public class LeaderBoardEntry implements Comparable<LeaderBoardEntry> {
    private int score;
    private String name;
    private Sprite sprite;
    private GameDuration duration;
    private Date date;

    public LeaderBoardEntry(int score, String name, Sprite sprite,
                            GameDuration duration, Date date) {
        this.score = score;
        this.name = name;
        this.sprite = sprite;
        this.duration = duration;
        this.date = date;
    }
    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }
    public Sprite getSprite() {
        return sprite;
    }
    public GameDuration getDuration() {
        return duration;
    }
    public Date getDate() {
        return date;
    }

    public int compareTo(LeaderBoardEntry lbe) {
        if (lbe.score != this.score) {
            return lbe.score - this.score;
        }
        return (int) (this.date.getTime() - lbe.date.getTime());
    }
}
