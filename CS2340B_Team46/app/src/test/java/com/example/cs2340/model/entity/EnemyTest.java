package com.example.cs2340.model.entity;

import static org.junit.Assert.assertEquals;

import com.example.cs2340.R;

import org.junit.Test;

public class EnemyTest {
    @Test
    public void attackPlayerTest() {
        int strength = 10;
        Enemy enemy = new TAEnemy(750, 250, strength, 100, R.drawable.enemyprof);
        Player player = Player.getInstance();
        int currHealth = player.getCurrentHealth();
        enemy.attackPlayer(player);

        assertEquals(currHealth - strength, player.getCurrentHealth());

    }
    @Test
    public void checkCollisionTest() {
        int strength = 10;
        Enemy enemy = new TAEnemy(750, 250, strength, 100, R.drawable.enemyprof);
        Player player = Player.getInstance();
        int health = player.getCurrentHealth() - strength;

        player.setY(0);
        player.setX(0);
        enemy.setX(0);
        enemy.setY(0);

        enemy.checkCollision(player);

        assertEquals(health, player.getCurrentHealth());
    }
}