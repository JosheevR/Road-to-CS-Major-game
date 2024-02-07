package com.example.cs2340;

import com.example.cs2340.model.GameConfiguration;
import com.example.cs2340.model.GameDifficulty;
import com.example.cs2340.model.Sprite;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameConfigurationTest {
    @Test
    public void testConstructor() {
        String playerName = "Leeroy Jenkins";
        GameDifficulty gameDifficulty = GameDifficulty.HARD;
        Sprite sprite = Sprite.YELLOW;
        GameConfiguration gameConfiguration = new GameConfiguration(
                playerName, gameDifficulty, sprite);

        assertEquals(playerName, gameConfiguration.getPlayerName());
        assertEquals(sprite, gameConfiguration.getSprite());
        assertEquals(gameDifficulty, gameConfiguration.getDifficulty());
    }

    @Test
    public void testSetDifficulty() {
        String playerName = "Leeroy Jenkins";
        GameDifficulty gameDifficulty = GameDifficulty.HARD;
        Sprite sprite = Sprite.YELLOW;
        GameConfiguration gameConfiguration = new GameConfiguration(
                playerName, gameDifficulty, sprite);

        GameDifficulty newGameDifficulty = GameDifficulty.EASY;

        gameConfiguration.setDifficulty(newGameDifficulty);
        assertEquals(newGameDifficulty, gameConfiguration.getDifficulty());
    }

}