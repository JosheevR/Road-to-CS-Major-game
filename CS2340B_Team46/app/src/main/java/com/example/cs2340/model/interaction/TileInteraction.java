package com.example.cs2340.model.interaction;

import com.example.cs2340.model.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TileInteraction {
    private List<TileInteractionListener> subs = new ArrayList<>();
    public void subscribe(TileInteractionListener sub) {
        subs.add(sub);
    }
    public void unsubscribe(TileInteractionListener sub) {
        subs.remove(sub);
    }
    public void notifySubscribers(int room, int x, int y, Player p) {
        for (TileInteractionListener sub: subs) {
            sub.interact(room, x, y, p);
        }
    }
}
