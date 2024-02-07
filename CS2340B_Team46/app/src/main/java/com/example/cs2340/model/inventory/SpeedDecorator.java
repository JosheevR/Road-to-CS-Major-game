package com.example.cs2340.model.inventory;

import com.example.cs2340.model.entity.Player;

public class SpeedDecorator extends PowerUpDecorator {
    public SpeedDecorator(PowerUp p) {
        super(p);
    }

    @Override
    public void buffPlayer() {
        Player player = Player.getInstance();
        int curSpeed = player.getSpeed();
        player.setSpeed(curSpeed + (curSpeed + 5));
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
