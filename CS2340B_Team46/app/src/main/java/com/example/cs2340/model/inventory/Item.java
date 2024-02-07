package com.example.cs2340.model.inventory;

public abstract class Item {
    private String name;
    private int price;
    private int sprite;
    private float xPos;
    private float yPos;

    public Item(String name, int price, int sprite) {
        this.name = name;
        this.price = price;
        this.sprite = sprite;
    }
    public int getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setXPos(float xPos) {
        this.xPos = xPos;
    }

    public void setYPos(float yPos) {
        this.yPos = yPos;
    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
