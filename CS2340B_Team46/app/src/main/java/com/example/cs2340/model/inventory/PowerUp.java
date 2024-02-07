package com.example.cs2340.model.inventory;

import com.example.cs2340.model.interaction.PlayerPowerUpInterActionListener;

public interface PowerUp extends PlayerPowerUpInterActionListener {
    void buffPlayer();

    float getX();

    float getY();
    int getSprite();
    void setX(float x);
    void setY(float y);

}
