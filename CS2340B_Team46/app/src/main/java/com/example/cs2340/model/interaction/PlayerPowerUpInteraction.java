package com.example.cs2340.model.interaction;

import java.util.ArrayList;
import java.util.List;

public class PlayerPowerUpInteraction {
    private final List<PlayerPowerUpInterActionListener> subs = new ArrayList<>();
    public void subscribe(PlayerPowerUpInterActionListener sub) {
        subs.add(sub);
    }
    public void unsubscribe(PlayerPowerUpInterActionListener sub) {
        subs.remove(sub);
    }
    public void notifySubscribersCollision() {
        for (PlayerPowerUpInterActionListener sub : subs) {
            sub.checkCollision();
        }
    }
}
