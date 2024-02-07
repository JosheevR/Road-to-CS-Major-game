package com.example.cs2340.model.interaction;

import com.example.cs2340.model.entity.Player;
import java.util.List;
import java.util.ArrayList;

public class PlayerEnemyInteraction {
    private final List<PlayerEnemyInteractionListener> subs = new ArrayList<>();
    public void subscribe(PlayerEnemyInteractionListener sub) {
        subs.add(sub);
    }
    public void unsubscribe(PlayerEnemyInteractionListener sub) {
        subs.remove(sub);
    }
    public void notifySubscribersCollision(Player player) {
        for (PlayerEnemyInteractionListener sub: subs) {
            sub.updatePlayerInformation(player);
            sub.checkCollision(player);
        }
    }

    public void notifySubscribersAttack(Player player) {
        for (PlayerEnemyInteractionListener sub: subs) {
            sub.checkAttackRange(player);
        }
    }
}
