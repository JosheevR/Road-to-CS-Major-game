package com.example.cs2340.model.inventory;

public abstract class Consumable extends Item {
    private int change;

    public Consumable(String name, int price, int sprite, int change) {
        super(name, price, sprite);
        this.change = change;
    }
}
