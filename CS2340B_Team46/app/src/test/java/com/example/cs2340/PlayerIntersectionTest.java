package com.example.cs2340;

import static org.junit.Assert.assertEquals;

import com.example.cs2340.model.Coord;
import com.example.cs2340.model.entity.Player;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class PlayerIntersectionTest {
    @Test
    public void testIntersectOne() {
        Player p = Player.getInstance();
        p.setX(190);
        p.setY(180);

        Set<Coord> expected = new HashSet<>();
        expected.add(new Coord(1, 1));

        assertEquals(expected, p.getIntersections());
    }

    @Test
    public void testIntersectMany() {
        Player p = Player.getInstance();
        p.setX(330);
        p.setY(290);

        Set<Coord> expected = new HashSet<>();
        expected.add(new Coord(1, 1));
        expected.add(new Coord(1, 2));
        expected.add(new Coord(2, 1));
        expected.add(new Coord(2, 2));

        assertEquals(expected, p.getIntersections());
    }
}
