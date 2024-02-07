package com.example.cs2340.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cs2340.viewmodel.ConfigViewModel;
import com.example.cs2340.R;
import com.example.cs2340.databinding.ActivityConfigBinding;

public class ConfigActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityConfigBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_config);
        ConfigViewModel viewModel = new ConfigViewModel();
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        viewModel.getStartAction().observe(this, c -> {
            Intent game = new Intent(this, GameActivity.class);
            game.putExtra("config", c);
            startActivity(game);
        });
    }

    @BindingAdapter({"toastMessage"})
    public static void showToast(View v, String msg) {
        if (msg != null) {
            Toast.makeText(v.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}