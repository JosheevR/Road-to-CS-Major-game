package com.example.cs2340.model.inventory;


import com.example.cs2340.model.entity.Player;

public abstract class PowerUpDecorator implements PowerUp {
    protected PowerUp powerUp;

    public PowerUpDecorator(PowerUp p) {
        powerUp = p;
    }
    @Override
    public void checkCollision() {
        Player player = Player.getInstance();
        float playerLeft = (player.getX() / 185);
        float playerRight = ((player.getX() + 95) / 185);
        float playerTop = (player.getY() / 175);
        float playerBottom = ((player.getY() + 120) / 175);

        float powerUpLeft = (powerUp.getX() / 185);
        float powerUpRight = ((powerUp.getX() + 95) / 185);
        float powerUpTop = (powerUp.getY() / 175);
        float powerUpBottom = ((powerUp.getY() + 120) / 175);

        if (playerRight >= powerUpLeft && playerLeft <= powerUpRight
                && playerBottom >= powerUpTop && playerTop <= powerUpBottom) {
            buffPlayer();
            setX(-2000);
            setY(-2000);
        }

    }

    @Override
    public float getX() {
        return powerUp.getX();
    }

    @Override
    public float getY() {
        return powerUp.getY();
    }

    @Override
    public void setX(float x) {
        powerUp.setX(x);
    }
    @Override
    public void setY(float y) {
        powerUp.setY(y);
    }
    @Override
    public int getSprite() {
        return powerUp.getSprite();
    }
}
