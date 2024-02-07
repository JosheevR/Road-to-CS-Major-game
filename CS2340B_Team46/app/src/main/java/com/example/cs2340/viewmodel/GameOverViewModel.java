package com.example.cs2340.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.cs2340.model.GameConfiguration;
import com.example.cs2340.model.GameState;
import com.example.cs2340.model.LeaderBoard;
import com.example.cs2340.model.LeaderBoardEntry;

import java.util.Date;
import java.util.List;

public class GameOverViewModel extends BaseObservable {
    private GameConfiguration config;
    private GameState state;
    public GameOverViewModel(GameConfiguration config, GameState state) {
        this.state = state;
        this.config = config;
        Date date = new Date();
        LeaderBoard lb = LeaderBoard.getInstance();
        LeaderBoardEntry entry = new LeaderBoardEntry(
                -1,
                config.getPlayerName(),
                config.getSprite(),
                state.getElapsedTime(),
                date
        );
        lb.addScore(entry);
        notifyPropertyChanged(BR.leaderboardEntries);
    }
    @Bindable
    public String getPlayerName() {
        return config.getPlayerName();
    }
    @Bindable
    public String getAttemptText() {
        return Integer.toString(state.getAttempt());
    }

    @Bindable
    public String getDifficulty() {
        return config.getDifficulty().toString();
    }
    @Bindable
    public String getScoreText() {
        return Integer.toString(0);
    }

    @Bindable
    public String getTimeText() {
        return state.getElapsedTime().toString();
    }
    @Bindable
    public List<LeaderBoardEntry> getLeaderboardEntries() {
        return LeaderBoard.getInstance().getEntries();
    }
}