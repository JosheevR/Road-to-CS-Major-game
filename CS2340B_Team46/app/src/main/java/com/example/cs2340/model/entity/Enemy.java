package com.example.cs2340.model.entity;

import com.example.cs2340.model.interaction.PlayerEnemyInteractionListener;

public abstract class Enemy extends Entity implements PlayerEnemyInteractionListener {
    protected EnemyType enemyType;
    protected float playerXPos;
    protected float playerYPos;

    protected int sprite;
    protected int direction;
    public Enemy(int maxHealth, int speed, int strength, int hunger, int sprite) {
        super(maxHealth, speed, strength, hunger);
        this.sprite = sprite;
        direction = 1;
    }

    @Override
    public void updatePlayerInformation(Player player) {
        playerXPos = player.getX();
        playerYPos = player.getY();
    }

    @Override
    public void checkCollision(Player player) {
        float playerLeft = (playerXPos / 185);
        float playerRight = ((playerXPos + 95) / 185);
        float playerTop = (playerYPos / 175);
        float playerBottom = ((playerYPos + 120) / 175);

        float enemyLeft = (xPos / 185);
        float enemyRight = ((xPos + 95) / 185);
        float enemyTop = (yPos / 175);
        float enemyBottom = ((yPos + 120) / 175);

        if (playerRight >= enemyLeft && playerLeft <= enemyRight
                && playerBottom >= enemyTop && playerTop <= enemyBottom) {
            attackPlayer(player);
        }
    }

    public void checkAttackRange(Player player) {
        float playerLeft = (playerXPos / 185);
        float playerRight = ((playerXPos + 95) / 185);
        float playerTop = (playerYPos / 175);
        float playerBottom = ((playerYPos + 120) / 175);

        float enemyLeft = (xPos / 185);
        float enemyRight = ((xPos + 95) / 185);
        float enemyTop = (yPos / 175);
        float enemyBottom = ((yPos + 120) / 175);

        if (playerRight >= enemyLeft && playerLeft <= enemyRight
                && playerBottom >= enemyTop && playerTop <= enemyBottom) {
            currentHealth -= 1000 * player.strength;
            if (currentHealth < 0) {
                currentHealth = 0;
            }
        }
    }

    public void attackPlayer(Player player) {
        player.setCurrentHealth(player.getCurrentHealth() - strength);
    }

    public int getSprite() {
        return sprite;
    }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
}
