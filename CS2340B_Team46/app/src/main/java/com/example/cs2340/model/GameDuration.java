package com.example.cs2340.model;

import java.io.Serializable;

public class GameDuration implements Serializable {
    private int duration;

    public GameDuration() {
        this(0);
    }

    public GameDuration(int duration) {
        this.duration = duration;
    }

    public int getValue() {
        return duration;
    }

    public void setValue(int duration) {
        this.duration = duration;
    }

    public int incrementValue() {
        return incrementValue(1);
    }

    public int incrementValue(int inc) {
        this.duration += inc;
        return this.duration;
    }

    public String toString() {
        int hours = duration / 3600;
        int minutes = (duration % 3600) / 60;
        int seconds = duration % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
