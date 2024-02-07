package com.example.cs2340;

import static org.junit.Assert.assertEquals;

import com.example.cs2340.model.GameMap;

import org.junit.Test;

public class GameMapTest {
    @Test
    public void testInitialRoomIsZero() {
        GameMap gameMap = new GameMap();
        assertEquals(0, gameMap.getCurrentRoomNumber());
    }

    @Test
    public void testSetCurrentRoomBounds() {
        GameMap gameMap = new GameMap();
        gameMap.setCurrentRoom(100);
        // there are not 100 rooms, so this shouldn't do anything
        assertEquals(0, gameMap.getCurrentRoomNumber());
    }

    @Test
    public void testSetCurrentRoom() {
        GameMap gameMap = new GameMap();
        gameMap.setCurrentRoom(1);
        assertEquals(1, gameMap.getCurrentRoomNumber());
    }

    @Test
    public void testMapSizeIsThree() {
        GameMap gameMap = new GameMap();
        assertEquals(4, gameMap.size());
    }
}
