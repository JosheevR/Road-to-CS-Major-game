package com.example.cs2340.model;

public class Room {
    private Tile[][] tiles;
    public static final int HEIGHT = 6;
    public static final int WIDTH = 12;

    public Room() {
        this(new Tile[HEIGHT][WIDTH]);
    }
    public Room(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public Room(int[][] tiles) {
        this.tiles = new Tile[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                this.tiles[i][j] = Tile.values()[tiles[i][j]];
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) {
            return null;
        }
        return tiles[y][x];
    }

    public void setTile(Tile tile, int x, int y) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) {
            return;
        }
        tiles[y][x] = tile;
    }
}
