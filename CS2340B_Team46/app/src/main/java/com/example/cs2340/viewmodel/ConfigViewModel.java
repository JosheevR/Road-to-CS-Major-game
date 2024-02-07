package com.example.cs2340.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cs2340.R;
import com.example.cs2340.model.GameConfiguration;
import com.example.cs2340.model.GameDifficulty;
import com.example.cs2340.model.Sprite;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConfigViewModel extends BaseObservable {
    private GameConfiguration config;
    private int difficulty;
    private int sprite;

    private String toastMessage;

    private static final Map<Integer, GameDifficulty> DIFF_MAP;
    private static final Map<Integer, Sprite> SPRITE_MAP;
    private MutableLiveData<GameConfiguration> startAction;

    static {
        Map<Integer, GameDifficulty> dMap = new HashMap<>();
        dMap.put(R.id.difficultyEasy, GameDifficulty.EASY);
        dMap.put(R.id.difficultyMed, GameDifficulty.MEDIUM);
        dMap.put(R.id.difficultyHard, GameDifficulty.HARD);
        DIFF_MAP = Collections.unmodifiableMap(dMap);

        Map<Integer, Sprite> sMap = new HashMap<>();
        sMap.put(R.id.sprite1, Sprite.YELLOW);
        sMap.put(R.id.sprite2, Sprite.PINK);
        sMap.put(R.id.sprite3, Sprite.GREEN);
        SPRITE_MAP = Collections.unmodifiableMap(sMap);
    }

    public ConfigViewModel() {
        config = new GameConfiguration("", GameDifficulty.EASY, Sprite.YELLOW);
        difficulty = R.id.difficultyEasy;
        sprite = R.id.sprite1;
        toastMessage = null;
        startAction = new MutableLiveData<>();
    }

    @Bindable
    public String getToastMessage() {
        return toastMessage;
    }

    public void setToastMessage(String msg) {
        toastMessage = msg;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public String getPlayerName() {
        return config.getPlayerName();
    }

    public void setPlayerName(String playerName) {
        config.setPlayerName(playerName.trim());
    }

    @Bindable
    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty) {
        if (!DIFF_MAP.containsKey(difficulty)) {
            return;
        }
        this.difficulty = difficulty;
        config.setDifficulty(DIFF_MAP.get(difficulty));
    }

    @Bindable
    public int getSprite() {
        return this.sprite;
    }

    public void setSprite(int sprite) {
        if (!SPRITE_MAP.containsKey(sprite)) {
            return;
        }
        this.sprite = sprite;
        config.setSprite(SPRITE_MAP.get(sprite));
    }

    public LiveData<GameConfiguration> getStartAction() {
        return startAction;
    }

    public void onStartClicked() {
        if (!validateName(config.getPlayerName())) {
            setToastMessage("Invalid name!");
            return;
        }

        startAction.setValue(config);
    }

    private boolean validateName(String name) {
        return name != null && !"".equals(name);
    }
}
