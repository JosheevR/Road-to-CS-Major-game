package com.example.cs2340.model.entity;

public class ProfessorEnemy extends Enemy {
    public ProfessorEnemy(int maxHealth, int speed, int strength, int hunger, int sprite) {
        super(maxHealth, speed, strength, hunger, sprite);
        enemyType = EnemyType.Professor;
    }
}
