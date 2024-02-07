package com.example.cs2340;

import static org.junit.Assert.*;

import com.example.cs2340.model.movement.DefaultSpeed;
import com.example.cs2340.model.movement.MoveStrategy;
import com.example.cs2340.model.entity.Player;

import org.junit.Test;

public class PlayerTest {
    @Test
    public void testMovePlayerUp() {
        MoveStrategy s = new DefaultSpeed();
        Player player = Player.getInstance();
        player.setMoveStrategy(s);
        int speed = 10;
        player.setSpeed(speed);
        player.setY(0);
        int expectedY = (int) player.getY() - speed;
        player.move(-player.getSpeed(), 0);

        assertEquals(expectedY, (int) player.getY());
    }

    @Test
    public void testMovePlayerDown() {
        MoveStrategy s = new DefaultSpeed();
        Player player = Player.getInstance();
        player.setMoveStrategy(s);
        int speed = 10;
        player.setSpeed(speed);
        player.setY(0);
        int expectedY = (int) player.getY() + speed;
        player.move(player.getSpeed(), 0);

        assertEquals(expectedY, (int) player.getY());
    }

    @Test
    public void testReturnToCenter() {
        Player player = Player.getInstance();
        player.setSpeed(5);
        MoveStrategy ms = new DefaultSpeed();
        player.setMoveStrategy(ms);
        player.setX(0);
        player.setY(0);
        player.move(player.getSpeed(), 0);
        player.move(-player.getSpeed(), 0);
        player.move(0, player.getSpeed());
        player.move(0, -player.getSpeed());

        assertEquals(0, (int) player.getX());
        assertEquals(0, (int) player.getY());
    }

    @Test
    public void testMovePlayerHorizontally() {
        Player player = Player.getInstance();
        player.setSpeed(15);
        MoveStrategy ms = new DefaultSpeed();
        player.setMoveStrategy(ms);
        player.setX(10);
        player.setY(10);
        player.move(-player.getSpeed(), 0);
        player.move(-player.getSpeed(), 0);
        player.move(player.getSpeed(), 0);


        assertEquals(-5, (int) player.getX());
        assertEquals(10, (int) player.getY());
    }
}