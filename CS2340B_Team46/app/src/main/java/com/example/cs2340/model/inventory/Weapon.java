package com.example.cs2340.model.inventory;

import com.example.cs2340.model.entity.Player;

public class Weapon extends Item {
    private int durability;
    private int damage;

    public Weapon(String name, int price, int sprite, int durability, int damage) {
        super(name, price, sprite);
        this.durability = durability;
        this.damage = damage;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void checkCollision(Player player) {
        float playerXPos = player.getX();
        float playerYPos = player.getY();

        float playerLeft = (playerXPos / 185);
        float playerRight = ((playerXPos + 95) / 185);
        float playerTop = (playerYPos / 175);
        float playerBottom = ((playerYPos + 120) / 175);

        float itemLeft = (this.getXPos() / 185);
        float itemRight = ((this.getXPos() + 95) / 185);
        float itemTop = (this.getYPos() / 175);
        float itemBottom = ((this.getYPos() + 120) / 175);

        if (playerRight >= itemLeft && playerLeft <= itemRight
                && playerBottom >= itemTop && playerTop <= itemBottom) {
            setXPos(-100);
            setYPos(-100);
            player.setWeapon(this);
        }
    }

}
