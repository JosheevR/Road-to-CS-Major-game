package com.example.cs2340.model.entity;

import static org.junit.Assert.assertEquals;

import com.example.cs2340.model.GameDifficulty;

import org.junit.Test;

public class EnemyDifficultyTest {
    @Test
    public void testEasyDifficultyTextbookEnemy() {
        Enemy e = EnemyFactory.createEnemy(EnemyType.Textbook, GameDifficulty.EASY.ordinal());
        assertEquals(e.getCurrentHealth(), 15);
        assertEquals(e.getSpeed(), 20);
        assertEquals(e.getStrength(), 30);
        assertEquals(e.getHunger(), 30);
    }

    @Test
    public void testEasyDifficultyTAEnemy() {
        Enemy e = EnemyFactory.createEnemy(EnemyType.TA, GameDifficulty.EASY.ordinal());
        assertEquals(e.getCurrentHealth(), 100);
        assertEquals(e.getSpeed(), 50);
        assertEquals(e.getStrength(), 10);
        assertEquals(e.getHunger(), 100);
    }

    @Test
    public void testEasyDifficultyHeadTAEnemy() {
        Enemy e = EnemyFactory.createEnemy(EnemyType.HeadTA, GameDifficulty.EASY.ordinal());
        assertEquals(e.getCurrentHealth(), 125);
        assertEquals(e.getSpeed(), 70);
        assertEquals(e.getStrength(), 12);
        assertEquals(e.getHunger(), 100);
    }

    @Test
    public void testEasyDifficultyProfEnemy() {
        Enemy e = EnemyFactory.createEnemy(EnemyType.Professor, GameDifficulty.EASY.ordinal());
        assertEquals(e.getCurrentHealth(), 150);
        assertEquals(e.getSpeed(), 90);
        assertEquals(e.getStrength(), 15);
        assertEquals(e.getHunger(), 100);
    }
    @Test
    public void testMedDifficultyTextbookEnemy() {
        Enemy e = EnemyFactory.createEnemy(EnemyType.Textbook, GameDifficulty.MEDIUM.ordinal());
        assertEquals(e.getCurrentHealth(), 30);
        assertEquals(e.getSpeed(), 20);
        assertEquals(e.getStrength(), 37);
        assertEquals(e.getHunger(), 30);
    }

    @Test
    public void testMedDifficultyTAEnemy() {
        Enemy e = EnemyFactory.createEnemy(EnemyType.TA, GameDifficulty.MEDIUM.ordinal());
        assertEquals(e.getCurrentHealth(), 115);
        assertEquals(e.getSpeed(), 50);
        assertEquals(e.getStrength(), 17);
        assertEquals(e.getHunger(), 100);
    }

    @Test
    public void testMedDifficultyHeadTAEnemy() {
        Enemy e = EnemyFactory.createEnemy(EnemyType.HeadTA, GameDifficulty.MEDIUM.ordinal());
        assertEquals(e.getCurrentHealth(), 140);
        assertEquals(e.getSpeed(), 70);
        assertEquals(e.getStrength(), 19);
        assertEquals(e.getHunger(), 100);
    }

    @Test
    public void testMedDifficultyProfEnemy() {
        Enemy e = EnemyFactory.createEnemy(EnemyType.Professor, GameDifficulty.MEDIUM.ordinal());
        assertEquals(e.getCurrentHealth(), 165);
        assertEquals(e.getSpeed(), 90);
        assertEquals(e.getStrength(), 22);
        assertEquals(e.getHunger(), 100);
    }

    @Test
    public void testHardDifficultyTextbookEnemy() {
        Enemy e = EnemyFactory.createEnemy(EnemyType.Textbook, GameDifficulty.HARD.ordinal());
        assertEquals(e.getCurrentHealth(), 45);
        assertEquals(e.getSpeed(), 20);
        assertEquals(e.getStrength(), 45);
        assertEquals(e.getHunger(), 30);
    }

    @Test
    public void testHardDifficultyTAEnemy() {
        Enemy e = EnemyFactory.createEnemy(EnemyType.TA, GameDifficulty.HARD.ordinal());
        assertEquals(e.getCurrentHealth(), 130);
        assertEquals(e.getSpeed(), 50);
        assertEquals(e.getStrength(), 25);
        assertEquals(e.getHunger(), 100);
    }

    @Test
    public void testHardDifficultyHeadTAEnemy() {
        Enemy e = EnemyFactory.createEnemy(EnemyType.HeadTA, GameDifficulty.HARD.ordinal());
        assertEquals(e.getCurrentHealth(), 155);
        assertEquals(e.getSpeed(), 70);
        assertEquals(e.getStrength(), 27);
        assertEquals(e.getHunger(), 100);
    }

    @Test
    public void testHardDifficultyProfEnemy() {
        Enemy e = EnemyFactory.createEnemy(EnemyType.Professor, GameDifficulty.HARD.ordinal());
        assertEquals(e.getCurrentHealth(), 180);
        assertEquals(e.getSpeed(), 90);
        assertEquals(e.getStrength(), 30);
        assertEquals(e.getHunger(), 100);
    }

}
