package com.example.cs2340;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.example.cs2340.model.GameDuration;
import com.example.cs2340.model.LeaderBoard;
import com.example.cs2340.model.LeaderBoardEntry;
import com.example.cs2340.model.Sprite;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class LeaderboardTest {
    @Test
    public void testEntrySameScore() {
        LeaderBoardEntry e1 = new LeaderBoardEntry(2, "entry",
                Sprite.GREEN, new GameDuration(3), new Date(999));
        LeaderBoardEntry e2 = new LeaderBoardEntry(2, "entry",
                Sprite.GREEN, new GameDuration(3), new Date(99));
        e1.compareTo(e2);

        assertTrue(e1.compareTo(e2) > 0);
    }

    @Test
    public void testLeaderboard() {
        LeaderBoardEntry e1 = new LeaderBoardEntry(5, "entry",
                Sprite.GREEN, new GameDuration(3), new Date(999));
        LeaderBoardEntry e2 = new LeaderBoardEntry(1, "entry",
                Sprite.GREEN, new GameDuration(3), new Date(999));
        LeaderBoardEntry e3 = new LeaderBoardEntry(2, "entry",
                Sprite.GREEN, new GameDuration(3), new Date(999));
        LeaderBoardEntry e4 = new LeaderBoardEntry(7, "entry",
                Sprite.GREEN, new GameDuration(3), new Date(999));
        LeaderBoardEntry e5 = new LeaderBoardEntry(6, "entry",
                Sprite.GREEN, new GameDuration(3), new Date(999));
        LeaderBoardEntry e6 = new LeaderBoardEntry(9, "entry",
                Sprite.GREEN, new GameDuration(3), new Date(999));
        LeaderBoardEntry e7 = new LeaderBoardEntry(3, "entry",
                Sprite.GREEN, new GameDuration(3), new Date(999));

        LeaderBoard lb = LeaderBoard.getInstance();
        lb.clear();
        lb.addScore(e1);
        lb.addScore(e2);
        lb.addScore(e3);
        lb.addScore(e4);
        lb.addScore(e5);
        lb.addScore(e6);
        lb.addScore(e7);

        ArrayList<LeaderBoardEntry> lbTest = new ArrayList<>();
        lbTest.add(e1);
        lbTest.add(e2);
        lbTest.add(e3);
        lbTest.add(e4);
        lbTest.add(e5);
        lbTest.add(e6);
        lbTest.add(e7);
        Collections.sort(lbTest);

        assertEquals(lbTest, lb.getEntries());
    }

    @Test
    public void testSingletonLeaderboard() {
        LeaderBoard lb1 = LeaderBoard.getInstance();
        lb1.addScore(new LeaderBoardEntry(5, "entry",
                Sprite.GREEN, new GameDuration(3), new Date(999)));
        LeaderBoard lb2 = LeaderBoard.getInstance();

        assertSame(lb1, lb2);
    }
}
