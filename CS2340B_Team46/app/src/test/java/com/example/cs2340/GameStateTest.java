package com.example.cs2340;
import static org.junit.Assert.assertEquals;
import com.example.cs2340.model.GameState;
import org.junit.Test;
public class GameStateTest {
    @Test
    public void testConstruct() {
        GameState gs = new GameState(1, 10);
        assertEquals(gs.getAttempt(), 1);
        assertEquals(gs.getScore(), 10);
        assertEquals(gs.getElapsedTime().getValue(), 0);
    }

    @Test
    public void testGetSet() {
        GameState gs = new GameState(1, 10);
        gs.setAttempt(2);
        assertEquals(gs.getAttempt(), 2);
        gs.setScore(5);
        assertEquals(gs.getScore(), 5);
    }
}
