package com.example.cs2340.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340.R;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onContinue(View v) {
        Intent game = new Intent(WelcomeActivity.this, ConfigActivity.class);
        startActivity(game);
    }

    public void onExit(View v) {
        finish();
        System.exit(0);
    }
}

