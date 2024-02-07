package com.example.cs2340.model.entity;

import com.example.cs2340.model.inventory.Inventory;
import com.example.cs2340.model.inventory.Weapon;

public class Player extends Entity {
    private static Player player;
    private Inventory inventory;
    private Weapon weapon;

    private Player(int maxHealth, int speed, int strength, int hunger, Inventory inventory) {
        super(maxHealth, speed, strength, hunger);
        this.inventory = inventory;
        this.weapon = null;
    }

    private Player() {
        this(0, 0, 0, 0, new Inventory());
    }

    public static Player getInstance() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void resetInventory() {
        inventory = new Inventory();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
