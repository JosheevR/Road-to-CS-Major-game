package com.example.cs2340.model.inventory;

public class Inventory {
    private Item[] items;

    private int selectedItem;

    public Inventory() {
        items = new Item[5];
        selectedItem = 0;
    }

    public Item getSelectedItem() {
        return items[selectedItem];
    }

    public void selectNewItem(int i) {
        if (i >= 0 && i < 5) {
            selectedItem = i;
        }
    }

    public void addItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return;
            }
        }
    }

    public Item dropItem(int i) {
        if (i < 0 || i >= 5) {
            return null;
        }
        Item item = items[i];
        items[i] = null;
        return item;
    }

    public Item getItem(int i) {
        if (i < 0 || i >= 5) {
            return null;
        }
        return items[i];
    }
    public int getInventorySize() {
        return items.length;
    }
}
