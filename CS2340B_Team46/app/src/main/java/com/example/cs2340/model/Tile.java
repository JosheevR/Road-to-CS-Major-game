package com.example.cs2340.model;

import com.example.cs2340.R;

public enum Tile {
    Floor(R.drawable.floortile, false),
    Wall(R.drawable.walltile, true),
    Door(R.drawable.doortile, false),
    Table(R.drawable.tabletile, true),
    Laptop(R.drawable.tablelaptoptile, true);
    private final int id;
    private final boolean solid;

    Tile(int id, boolean solid) {
        this.id = id;
        this.solid = solid;
    }

    public int getId() {
        return id;
    }

    public boolean isSolid() {
        return this.solid;
    }
}
