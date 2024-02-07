package com.example.cs2340.model.inventory;

import com.example.cs2340.model.entity.Player;

public class DamageDecorator extends PowerUpDecorator {
    public DamageDecorator(PowerUp p) {
        super(p);
    }

    @Override
    public void buffPlayer() {
        Player player = Player.getInstance();
        Inventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getInventorySize(); i++) {
            if (inventory.getItem(i) instanceof Weapon) {
                Weapon weapon = (Weapon) inventory.getItem(i);
                weapon.setDamage(weapon.getDamage() + 5);
            }
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
