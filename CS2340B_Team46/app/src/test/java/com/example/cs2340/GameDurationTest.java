package com.example.cs2340;

import static org.junit.Assert.assertEquals;

import com.example.cs2340.model.GameDuration;

import org.junit.Test;

public class GameDurationTest {
    @Test
    public void testConstruct() {
        GameDuration d = new GameDuration(69420);
        assertEquals(69420, d.getValue());
    }

    @Test
    public void testGetSet() {
        GameDuration d = new GameDuration();
        d.setValue(69420);
        assertEquals(69420, d.getValue());
    }

    @Test
    public void testIncrement() {
        GameDuration d = new GameDuration();
        d.incrementValue();
        assertEquals(1, d.getValue());
        d.incrementValue(69419);
        assertEquals(69420, d.getValue());
    }

    @Test
    public void testToString() {
        GameDuration d = new GameDuration(69420);
        assertEquals("19:17:00", d.toString());
    }
}
