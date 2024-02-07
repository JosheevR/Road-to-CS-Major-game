package com.example.cs2340.model;

import com.example.cs2340.model.entity.Player;
import com.example.cs2340.model.interaction.TileInteractionListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameMap implements Serializable, TileInteractionListener {
    private static final List<Room> ROOMS;
    private int currentRoom;

    static {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(new int[][] {
                {1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 3, 3, 0, 0, 0, 3, 4, 0, 0, 2},
                {1, 0, 3, 3, 0, 0, 0, 3, 3, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1}
        }));
        rooms.add(new Room(new int[][] {
                {1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 3, 4, 0, 0, 0, 3, 3, 0, 0, 1},
                {1, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 1},
                {1, 0, 3, 0, 0, 0, 0, 0, 0, 4, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        }));
        rooms.add(new Room(new int[][] {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {2, 0, 3, 3, 3, 0, 0, 3, 4, 0, 0, 2},
                {1, 0, 0, 0, 0, 0, 0, 4, 3, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        }));
        rooms.add(new Room(new int[][] {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1}
        }));
        ROOMS = Collections.unmodifiableList(rooms);
    }

    public GameMap() {
        currentRoom = 0;
    }

    public Room getCurrentRoom() {
        return ROOMS.get(currentRoom);
    }

    public void setCurrentRoom(int newRoom) {
        if (newRoom >= 0 && newRoom < ROOMS.size()) {
            currentRoom = newRoom;
        }
    }

    public int size() {
        return ROOMS.size();
    }

    public int getCurrentRoomNumber() {
        return currentRoom;
    }

    @Override
    public void interact(int room, int x, int y, Player p) {
        switch (room) {
        case 0:
            if (x == 5 && y == 5) {
                setCurrentRoom(1);
            }
            if (x == 11 && y == 2) {
                setCurrentRoom(2);
            }
            if (x == 5 && y == 0) {
                setCurrentRoom(3);
            }
            break;
        case 1:
            if (x == 5 && y == 0) {
                setCurrentRoom(0);
            }
            break;
        case 2:
            if (x == 0 && y == 2) {
                setCurrentRoom(0);
            }
            break;
        case 3:
            if (x == 5 && y == 5) {
                setCurrentRoom(0);
            }
            break;
        default:
            break;
        }
    }
}
