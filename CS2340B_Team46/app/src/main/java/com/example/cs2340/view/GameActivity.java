package com.example.cs2340.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import android.content.Intent;
import android.view.KeyEvent;
import android.widget.GridView;

import com.example.cs2340.databinding.ActivityGameBinding;
import com.example.cs2340.model.GameConfiguration;
import com.example.cs2340.model.GameMap;
import com.example.cs2340.model.GameState;
import com.example.cs2340.R;
import com.example.cs2340.viewmodel.GameViewModel;

public class GameActivity extends AppCompatActivity {
    private GameViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGameBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        Intent game = getIntent();
        GameConfiguration config = (GameConfiguration) game.getSerializableExtra("config");
        int attempt = game.getIntExtra("attempt", 0) + 1;
        viewModel = new GameViewModel(attempt, config);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        viewModel.getEndAction().observe(this, s -> {
            endGame(s);
        });

        viewModel.getGameOverAction().observe(this, s -> {
            gameOver(s);
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        viewModel.keyPressed(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }
    @BindingAdapter({"map"})
    public static void showMap(GridView view, GameMap map) {
        MapAdapter adapter = new MapAdapter(view.getContext(), map);
        view.setAdapter(adapter);
    }
    private void endGame(GameState s) {
        Intent game = getIntent();
        Intent end = new Intent(this, EndActivity.class);
        end.putExtra("config", game.getSerializableExtra("config"));
        end.putExtra("state", s);
        startActivity(end);
    }
    private void gameOver(GameState s) {
        Intent game = getIntent();
        Intent gameover = new Intent(this, GameOverActivity.class);
        gameover.putExtra("config", game.getSerializableExtra("config"));
        gameover.putExtra("state", s);
        startActivity(gameover);
    }
}