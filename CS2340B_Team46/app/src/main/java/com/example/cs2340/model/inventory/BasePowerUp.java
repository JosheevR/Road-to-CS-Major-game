package com.example.cs2340.model.inventory;

import com.example.cs2340.model.entity.Player;
import com.example.cs2340.model.interaction.PlayerPowerUpInterActionListener;

public class BasePowerUp implements PowerUp, PlayerPowerUpInterActionListener {
    private int sprite;
    private float xPos;
    private float yPos;
    public BasePowerUp(int sprite, float xPos, float yPos) {
        this.sprite = sprite;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void buffPlayer() {
        Player player = Player.getInstance();
        player.setCurrentHealth(player.getCurrentHealth());
    }
    @Override
    public void checkCollision() {
        Player player = Player.getInstance();
        float playerLeft = (player.getX() / 185);
        float playerRight = ((player.getX() + 95) / 185);
        float playerTop = (player.getY() / 175);
        float playerBottom = ((player.getY() + 120) / 175);

        float powerUpLeft = (xPos / 185);
        float powerUpRight = ((xPos + 95) / 185);
        float powerUpTop = (yPos / 175);
        float powerUpBottom = ((yPos + 120) / 175);

        if (playerRight >= powerUpLeft && playerLeft <= powerUpRight
                && playerBottom >= powerUpTop && playerTop <= powerUpBottom) {
            buffPlayer();
        }

    }

    @Override
    public float getX() {
        return xPos;
    }

    @Override
    public float getY() {
        return yPos;
    }

    @Override
    public void setX(float x) {
        xPos = x;
    }

    @Override
    public void setY(float y) {
        yPos = y;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }

    @Override
    public int getSprite() {
        return sprite;
    }
}
