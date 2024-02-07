package com.example.cs2340.model.movement;

import com.example.cs2340.model.Coord;

public class DefaultSpeed implements MoveStrategy {
    public Coord move(float x, float y) {
        return new Coord((int) x, (int) y);
    }
}

