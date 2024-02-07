package com.example.cs2340.model.inventory;

import static org.junit.Assert.*;

import com.example.cs2340.R;

import org.junit.Test;

public class WeaponTest {
    @Test
    public void getDamage() {
        int damage = 10;
        Weapon weapon = new Weapon("sword", 10, R.drawable.sword, 10, damage);
        assertEquals(damage, weapon.getDamage());
    }

    @Test
    public void setDamage() {
        int initDamage = 0;
        int damage = 10;
        Weapon weapon = new Weapon("axe", 10, R.drawable.axe, 10, initDamage);

        weapon.setDamage(damage);
        assertEquals(damage, weapon.getDamage());
    }
}