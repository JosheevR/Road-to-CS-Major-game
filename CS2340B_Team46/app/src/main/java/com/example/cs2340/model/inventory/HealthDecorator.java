package com.example.cs2340.model.inventory;

import com.example.cs2340.model.entity.Player;

public class HealthDecorator extends PowerUpDecorator {
    public HealthDecorator(PowerUp p) {
        super(p);
    }

    @Override
    public void buffPlayer() {
        Player player = Player.getInstance();
        int curHP = player.getCurrentHealth();
        if (curHP + ((double) curHP / 2) > player.getMaxHealth()) {
            player.setCurrentHealth(player.getMaxHealth());
        } else {
            player.setCurrentHealth(curHP + (curHP / 2));
        }
    }
    @Override
    public float getX() {
        return super.powerUp.getX();
    }

    @Override
    public float getY() {
        return super.powerUp.getY();
    }
    @Override
    public int getSprite() {
        return super.powerUp.getSprite();
    }
}
