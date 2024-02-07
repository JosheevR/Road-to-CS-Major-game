package com.example.cs2340.model;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class LeaderBoard {
    private static LeaderBoard lb;
    private List<LeaderBoardEntry> backingList;
    private Date date;
    private double lowestScore = 0;
    private LeaderBoard() {
        backingList = new ArrayList<>();
        date = new Date();
    }
    public static LeaderBoard getInstance() {
        if (lb == null) {
            lb = new LeaderBoard();
        }
        return lb;
    }

    public List<LeaderBoardEntry> getEntries() {
        return backingList;
    }
    public void addScore(LeaderBoardEntry entry) {
        backingList.add(entry);
        Collections.sort(backingList);
    }
    public void clear() {
        backingList.clear();
    }
}
