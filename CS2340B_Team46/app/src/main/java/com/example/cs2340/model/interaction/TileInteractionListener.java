package com.example.cs2340.model.interaction;

import com.example.cs2340.model.entity.Player;

public interface TileInteractionListener {
    public void interact(int room, int x, int y, Player p);
}
