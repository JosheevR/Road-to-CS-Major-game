package com.example.cs2340.model.entity;

import com.example.cs2340.model.Coord;
import com.example.cs2340.model.movement.MoveStrategy;

import java.util.HashSet;
import java.util.Set;

public abstract class Entity {
    private int maxHealth;
    protected int currentHealth;
    private int speed;
    protected int strength;
    private int currentHunger;

    private int maxHunger;
    protected float xPos;
    protected float yPos;
    private MoveStrategy moveStrategy;

    public Entity(int maxHealth, int speed, int strength, int hunger) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.speed = speed;
        this.strength = strength;
        this.maxHunger = hunger;
        this.currentHunger = hunger;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void move(float x, float y) {
        Coord c = moveStrategy.move(x, y);
        xPos += c.getX();
        yPos += c.getY();
    }

    public Set<Coord> getIntersections() {
        Set<Coord> ints = new HashSet<>();
        int x1 = (int) ((getX()) / 185);
        int x2 = (int) ((getX() + 95) / 185);
        int y1 = (int) ((getY()) / 175);
        int y2 = (int) ((getY() + 120) / 175);
        ints.add(new Coord(x1, y1));
        ints.add(new Coord(x1, y2));
        ints.add(new Coord(x2, y1));
        ints.add(new Coord(x2, y2));
        return ints;
    }

    public float getX() {
        return xPos;
    }

    public void setX(float xPos) {
        this.xPos = xPos;
    }

    public float getY() {
        return yPos;
    }

    public void setY(float yPos) {
        this.yPos = yPos;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHunger() {
        return currentHunger;
    }

    public void setHunger(int hunger) {
        this.currentHunger = hunger;
    }
    public int getMaxHunger() {
        return maxHunger;
    }

    public void setMaxHunger(int hunger) {
        this.maxHunger = hunger;
    }
}
