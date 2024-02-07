package com.example.cs2340.model.entity;

public class TextbookEnemy extends Enemy {
    public TextbookEnemy(int maxHealth, int speed, int strength, int hunger, int sprite) {
        super(maxHealth, speed, strength, hunger, sprite);
        enemyType = EnemyType.Textbook;
    }
}
