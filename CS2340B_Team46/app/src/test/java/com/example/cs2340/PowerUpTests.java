package com.example.cs2340;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.cs2340.model.entity.Player;
import com.example.cs2340.model.inventory.BasePowerUp;
import com.example.cs2340.model.inventory.DamageDecorator;
import com.example.cs2340.model.inventory.DurabilityDecorator;
import com.example.cs2340.model.inventory.HealthDecorator;
import com.example.cs2340.model.inventory.PowerUp;
import com.example.cs2340.model.inventory.SpeedDecorator;
import com.example.cs2340.model.inventory.Weapon;

import org.junit.Test;

public class PowerUpTests {
    @Test
    public void testPowerUpGetX() {
        PowerUp p1 = new HealthDecorator(new BasePowerUp(R.drawable.hppowerup, 100, 100));
        assertEquals(p1.getX(), 100, 0);
        assertEquals(p1.getY(), 100, 0);
    }
    @Test
    public void testBuffPlayerHealth() {
        PowerUp p1 = new HealthDecorator(new BasePowerUp(R.drawable.hppowerup, 0, 0));
        Player player = Player.getInstance();
        player.setCurrentHealth(50);
        player.setMaxHealth(100);
        p1.buffPlayer();
        assertTrue(player.getCurrentHealth() > 50);
    }

    @Test
    public void testBuffPlayerDamage() {
        PowerUp p1 = new DamageDecorator(new BasePowerUp(R.drawable.dmgpowerup, 0, 0));
        Player player = Player.getInstance();
        Weapon w = new Weapon(null, 0, 0, 0, 10);
        player.getInventory().addItem(w);
        p1.buffPlayer();
        assertTrue(w.getDamage() > 10);
    }

    @Test
    public void testBuffPlayerDurability() {
        PowerUp p1 = new DurabilityDecorator(new BasePowerUp(R.drawable.durapowerup, 0, 0));
        Player player = Player.getInstance();
        Weapon w = new Weapon(null, 0, 0, 10, 0);
        player.getInventory().addItem(w);
        p1.buffPlayer();
        assertTrue(w.getDurability() > 10);
    }

    @Test
    public void testBuffPlayerSpeed() {
        PowerUp p1 = new SpeedDecorator(new BasePowerUp(R.drawable.speedpowerup, 0, 0));
        Player player = Player.getInstance();
        int oldSpeed = player.getSpeed();
        p1.buffPlayer();
        assertTrue(player.getSpeed() > oldSpeed);
    }
}
