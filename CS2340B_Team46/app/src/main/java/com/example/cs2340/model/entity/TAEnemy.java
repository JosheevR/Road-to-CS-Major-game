package com.example.cs2340.model.entity;

public class TAEnemy extends Enemy {
    public TAEnemy(int maxHealth, int speed, int strength, int hunger, int sprite) {
        super(maxHealth, speed, strength, hunger, sprite);
        enemyType = EnemyType.TA;
    }
}
