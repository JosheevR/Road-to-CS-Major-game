package com.example.cs2340.model.entity;

import com.example.cs2340.R;
public class EnemyFactory {
    public static Enemy createEnemy(EnemyType e, int gd) {
        int scale = gd * 15;
        switch (e) {
        case Textbook:
            return new TextbookEnemy(scale + 15, 20, scale / 2 + 30, 30, R.drawable.enemytextbook);
        case TA:
            return new TAEnemy(scale + 100, 50, scale / 2 + 10, 100, R.drawable.enemyta);
        case HeadTA:
            return new HeadTAEnemy(scale + 125, 70, scale / 2 + 12, 100, R.drawable.enemyta);
        case Professor:
            return new ProfessorEnemy(scale + 150, 90, scale / 2 + 15, 100, R.drawable.enemyprof);
        default:
            return null;
        }
    }
}
