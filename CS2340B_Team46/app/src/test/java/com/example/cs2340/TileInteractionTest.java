package com.example.cs2340;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.example.cs2340.model.GameMap;
import com.example.cs2340.model.entity.Player;
import com.example.cs2340.model.interaction.TileInteraction;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class TileInteractionTest {
    @Test
    public void testObserver() {
        AtomicBoolean subscriberCalled = new AtomicBoolean(false);
        Player player = Player.getInstance();

        TileInteraction ti = new TileInteraction();
        ti.subscribe((r, x, y, p) -> {
            assertEquals(1, r);
            assertEquals(2, x);
            assertEquals(3, y);
            assertSame(p, player);
            subscriberCalled.set(true);
        });

        ti.notifySubscribers(1, 2, 3, player);
        assertTrue(subscriberCalled.get());
    }

    @Test
    public void switchToRoom1From0() {
        TileInteraction ti = new TileInteraction();
        GameMap gm = new GameMap();
        ti.subscribe(gm);
        Player p = Player.getInstance();

        ti.notifySubscribers(0, 5, 5, p);
        assertEquals(1, gm.getCurrentRoomNumber());
    }

    @Test
    public void switchToRoom2From0() {
        TileInteraction ti = new TileInteraction();
        GameMap gm = new GameMap();
        ti.subscribe(gm);
        Player p = Player.getInstance();
        ti.notifySubscribers(0, 11, 2, p);
        assertEquals(2, gm.getCurrentRoomNumber());
    }

    @Test
    public void switchToRoom3From0() {
        TileInteraction ti = new TileInteraction();
        GameMap gm = new GameMap();
        ti.subscribe(gm);
        Player p = Player.getInstance();
        ti.notifySubscribers(0, 5, 0, p);
        assertEquals(3, gm.getCurrentRoomNumber());
    }

    @Test
    public void noRoomSwitch() {
        TileInteraction ti = new TileInteraction();
        GameMap gm = new GameMap();
        ti.subscribe(gm);
        Player p = Player.getInstance();
        ti.notifySubscribers(0, 11, 0, p);
        assertEquals(0, gm.getCurrentRoomNumber());
    }
}
