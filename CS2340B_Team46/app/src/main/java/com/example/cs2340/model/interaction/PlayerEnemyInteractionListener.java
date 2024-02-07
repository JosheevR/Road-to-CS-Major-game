package com.example.cs2340.model.interaction;

import com.example.cs2340.model.entity.Player;

public interface PlayerEnemyInteractionListener {
    void updatePlayerInformation(Player player);

    void checkCollision(Player player);

    void checkAttackRange(Player player);
}
