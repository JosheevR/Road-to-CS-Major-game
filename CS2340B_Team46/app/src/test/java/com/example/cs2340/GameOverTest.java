package com.example.cs2340;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.cs2340.model.GameConfiguration;
import com.example.cs2340.model.GameDifficulty;
import com.example.cs2340.model.GameState;
import com.example.cs2340.viewmodel.GameOverViewModel;

public class GameOverTest {
    @Test
    public void testViewModelConfig() {
        GameConfiguration config = new GameConfiguration("Throk", GameDifficulty.EASY, null);
        GameState state = new GameState(1, 10);
        GameOverViewModel vm = new GameOverViewModel(config, state);
        assertEquals("Throk", vm.getPlayerName());
        assertEquals("Easy", vm.getDifficulty());
    }
    @Test
    public void testViewModelState() {
        GameConfiguration config = new GameConfiguration("Throk", GameDifficulty.EASY, null);
        GameState state = new GameState(1, 10);
        GameOverViewModel vm = new GameOverViewModel(config, state);
        assertEquals("1", vm.getAttemptText());
        assertEquals("0", vm.getScoreText());
    }
}
