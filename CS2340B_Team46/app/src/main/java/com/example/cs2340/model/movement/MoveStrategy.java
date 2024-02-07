package com.example.cs2340.model.movement;

import com.example.cs2340.model.Coord;

public interface MoveStrategy {
    public Coord move(float x, float y);
}
