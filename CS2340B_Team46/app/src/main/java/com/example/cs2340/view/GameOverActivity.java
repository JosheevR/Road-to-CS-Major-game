package com.example.cs2340.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.cs2340.R;
import com.example.cs2340.databinding.ActivityGameOverBinding;
import com.example.cs2340.model.GameConfiguration;
import com.example.cs2340.model.GameState;
import com.example.cs2340.model.LeaderBoardEntry;
import com.example.cs2340.viewmodel.GameOverViewModel;

import java.text.DateFormat;
import java.util.List;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGameOverBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_game_over);
        Intent gameOver = getIntent();
        GameConfiguration config = (GameConfiguration) gameOver.getSerializableExtra("config");
        GameState state = (GameState)  gameOver.getSerializableExtra("state");
        GameOverViewModel viewModel = new GameOverViewModel(config, state);

        binding.setViewModel(viewModel);
        binding.executePendingBindings();
    }

    @BindingAdapter({"lbEntriesGameOver"})
    public static void showLeaderboard(TableLayout table, List<LeaderBoardEntry> lbEntries) {
        Context ctx = table.getContext();
        for (LeaderBoardEntry entry : lbEntries) {
            if (entry.getScore() == -1) {
                continue;
            }
            TableRow row = new TableRow(ctx);
            TextView name = new TextView(ctx);
            name.setText(entry.getName());
            TextView sprite = new TextView(ctx);
            sprite.setText(entry.getSprite().toString());
            TextView score = new TextView(ctx);
            score.setText(Integer.toString(entry.getScore()));
            TextView duration = new TextView(ctx);
            duration.setText(entry.getDuration().toString());
            TextView date = new TextView(ctx);
            date.setText(DateFormat.getDateInstance().format(entry.getDate()));
            row.addView(name);
            row.addView(sprite);
            row.addView(score);
            row.addView(duration);
            row.addView(date);
            table.addView(row);
        }
    }

    public void onRestart(View v) {
        Intent config = new Intent(this, ConfigActivity.class);
        startActivity(config);
    }
}