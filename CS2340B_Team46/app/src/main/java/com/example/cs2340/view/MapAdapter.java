package com.example.cs2340.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.cs2340.model.GameMap;
import com.example.cs2340.model.Room;

public class MapAdapter extends BaseAdapter {
    private final GameMap gameMap;
    private final Context context;

    public MapAdapter(Context context, GameMap gameMap) {
        this.gameMap = gameMap;
        this.context = context;
    }
    @Override
    public int getCount() {
        return Room.HEIGHT * Room.WIDTH;
    }

    @Override
    public Object getItem(int i) {
        return gameMap.getCurrentRoom().getTile(i % Room.WIDTH, i / Room.WIDTH);
    }

    @Override
    public long getItemId(int i) {
        return gameMap.getCurrentRoom().getTile(i % Room.WIDTH, i / Room.WIDTH).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int id = gameMap.getCurrentRoom().getTile(i % Room.WIDTH, i / Room.WIDTH).getId();
        View tileView = new View(context);
        tileView.setBackgroundResource(id);
        return tileView;
    }
}
