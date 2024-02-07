package com.example.cs2340.viewmodel;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cs2340.BR;
import com.example.cs2340.R;
import com.example.cs2340.model.Coord;
import com.example.cs2340.model.entity.EnemyFactory;
import com.example.cs2340.model.entity.EnemyType;
import com.example.cs2340.model.interaction.PlayerEnemyInteraction;
import com.example.cs2340.model.interaction.PlayerPowerUpInteraction;
import com.example.cs2340.model.interaction.TileInteractionListener;
import com.example.cs2340.model.inventory.BasePowerUp;
import com.example.cs2340.model.inventory.DamageDecorator;
import com.example.cs2340.model.inventory.DurabilityDecorator;
import com.example.cs2340.model.inventory.HealthDecorator;
import com.example.cs2340.model.inventory.Item;
import com.example.cs2340.model.inventory.PowerUp;
import com.example.cs2340.model.inventory.SpeedDecorator;
import com.example.cs2340.model.inventory.Weapon;
import com.example.cs2340.model.movement.DefaultSpeed;
import com.example.cs2340.model.GameConfiguration;
import com.example.cs2340.model.GameMap;
import com.example.cs2340.model.GameState;
import com.example.cs2340.model.interaction.TileInteraction;
import com.example.cs2340.model.movement.MoveStrategy;
import com.example.cs2340.model.entity.Player;
import com.example.cs2340.model.Sprite;
import com.example.cs2340.model.Tile;
import com.example.cs2340.model.entity.Enemy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class GameViewModel extends BaseObservable implements TileInteractionListener {
    private GameState state;
    private GameConfiguration config;
    private Timer timer;
    private Timer collisionTimer;
    private GameMap map;
    private static final Map<Sprite, Map<String, Integer>> SPRITE_MAP;
    private MutableLiveData<GameState> endAction;
    private MutableLiveData<GameState> gameOverAction;
    private Player player;
    private MoveStrategy moveStrategy;
    private TileInteraction tileInteraction;
    private PlayerEnemyInteraction playerEnemyInteraction;
    private PlayerPowerUpInteraction playerPowerUpInteraction;

    private EnemyFactory enemyFactory;
    private List<Enemy> enemies;
    private Enemy enemy1;
    private Enemy enemy2;
    private PowerUp p1;
    private PowerUp p2;

    private Weapon weapon;
    private int attackTime;
    private int attackVisibility;
    private boolean isAttacking;

    static {
        Map<Sprite, Map<String, Integer>> sMap = new HashMap<>();

        Map<String, Integer> yellowMap = new HashMap<>();
        yellowMap.put("none", R.drawable.sprite1);
        yellowMap.put("hoe", R.drawable.sprite1hoe);
        yellowMap.put("sword", R.drawable.sprite1sword);
        yellowMap.put("axe", R.drawable.sprite1axe);
        sMap.put(Sprite.YELLOW, Collections.unmodifiableMap(yellowMap));

        Map<String, Integer> pinkMap = new HashMap<>();
        pinkMap.put("none", R.drawable.sprite2);
        pinkMap.put("hoe", R.drawable.sprite2hoe);
        pinkMap.put("sword", R.drawable.sprite2sword);
        pinkMap.put("axe", R.drawable.sprite2axe);
        sMap.put(Sprite.PINK, Collections.unmodifiableMap(pinkMap));

        Map<String, Integer> greenMap = new HashMap<>();
        greenMap.put("none", R.drawable.sprite3);
        greenMap.put("hoe", R.drawable.sprite3hoe);
        greenMap.put("sword", R.drawable.sprite3sword);
        greenMap.put("axe", R.drawable.sprite3axe);
        sMap.put(Sprite.GREEN, Collections.unmodifiableMap(greenMap));

        SPRITE_MAP = Collections.unmodifiableMap(sMap);
    }

    public GameViewModel(int attempt, GameConfiguration config) {
        int maxHealth = 100 / (config.getDifficulty().ordinal() + 1);
        map = new GameMap();
        this.state = new GameState(attempt, 0);

        tileInteraction = new TileInteraction();
        tileInteraction.subscribe(map);
        tileInteraction.subscribe(this);

        moveStrategy = new DefaultSpeed();
        player = Player.getInstance();
        player.setMaxHealth(maxHealth);
        player.setCurrentHealth(maxHealth);
        player.setSpeed(15);
        player.setMoveStrategy(moveStrategy);
        player.setStrength(10);
        player.setHunger(100);
        player.setMaxHunger(100);
        startPlayerPosition();
        player.setWeapon(null);
        attackVisibility = View.INVISIBLE;
        isAttacking = false;

        this.config = config;

        enemies = new ArrayList<Enemy>();
        playerEnemyInteraction = new PlayerEnemyInteraction();
        this.enemyFactory = new EnemyFactory();
        spawnEnemies(0);
        playerEnemyInteraction.subscribe(enemy1);
        playerEnemyInteraction.subscribe(enemy2);


        playerPowerUpInteraction = new PlayerPowerUpInteraction();
        spawnPowerUps(0);
        playerPowerUpInteraction.subscribe(p1);
        playerPowerUpInteraction.subscribe(p2);

        spawnWeapon(0);


        endAction = new MutableLiveData<>();
        gameOverAction = new MutableLiveData<>();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tickClock();
            }
        }, 0, 1000);
        collisionTimer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tickCollisionClock();
            }
        }, 0, 100);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                weaponCollisionClock();
            }
        }, 0, 10);

        player.getInventory().addItem(new Item("Potion", 10, R.drawable.potion) {

        });

    }

    public LiveData<GameState> getEndAction() {
        return endAction;
    }
    public LiveData<GameState> getGameOverAction() {
        return gameOverAction;
    }

    private void tickClock() {
        int time = state.getElapsedTime().incrementValue();
        notifyPropertyChanged(BR.timerText);

        if (time % 10 == 0) {
            player.setHunger(player.getHunger() - 1);
            notifyPropertyChanged(BR.hungerText);
        }

        if (time % 30 == 0) {
            if (player.getHunger() < 60 && player.getCurrentHealth() > 30) {
                player.setCurrentHealth(player.getCurrentHealth() - 3);
            } else if (player.getHunger() >= 90 && player.getCurrentHealth() < 100) {
                player.setCurrentHealth(player.getCurrentHealth() + 1);
            }
            notifyPropertyChanged(BR.healthText);
        }
    }

    private void weaponCollisionClock() {
        weapon.checkCollision(player);
        notifyPropertyChanged(BR.weaponX);
        notifyPropertyChanged(BR.weaponY);
        notifyPropertyChanged(BR.spriteResource);
    }

    public void spawnWeapon(int room) {
        switch (room) {
        case 0:
            weapon = new Weapon("hoe", 0, R.drawable.hoe, 1, 1000);
            weapon.setXPos(225);
            weapon.setYPos(500);
            break;
        case 1:
            weapon = new Weapon("sword", 0, R.drawable.sword, 1, 1000);
            weapon.setXPos(750);
            weapon.setYPos(500);
            break;
        case 2:
            weapon = new Weapon("sword", 0, R.drawable.sword, 1, 1000);
            weapon.setXPos(225);
            weapon.setYPos(500);
            break;
        case 3:
            weapon = new Weapon("axe", 0, R.drawable.axe, 1, 1000);
            weapon.setXPos(225);
            weapon.setYPos(500);
            break;
        default:
            return;
        }
        notifyPropertyChanged(BR.weaponX);
        notifyPropertyChanged(BR.weaponY);
        notifyPropertyChanged(BR.weaponSprite);
    }

    private void tickCollisionClock() {
        moveEnemy(enemy1, 1);
        moveEnemy(enemy2, 0);
        notifyPropertyChanged(BR.enemy1X);
        notifyPropertyChanged(BR.enemy2Y);
        checkPlayerHealth(player);
        notifyPropertyChanged(BR.healthText);
    }

    public void moveEnemy(Enemy enemy, int orientation) {
        float oldX = enemy.getX();
        float oldY = enemy.getY();
        Set<Coord> oldInts = enemy.getIntersections();
        if (orientation == 1) {
            enemy.move(enemy.getDirection() * enemy.getSpeed(), 0);
        } else {
            enemy.move(0, enemy.getDirection() * enemy.getSpeed());

        }
        Set<Coord> newInts = enemy.getIntersections();
        newInts.removeAll(oldInts);
        int room = map.getCurrentRoomNumber();
        for (Coord i : newInts) {
            int x = i.getX();
            int y = i.getY();
            Tile t = map.getCurrentRoom().getTile(x, y);
            if (t != null && t.isSolid()) {
                // flip direction and reject movement
                enemy.setX(oldX);
                enemy.setY(oldY);
                enemy.setDirection(enemy.getDirection() * (-1));
                return;
            }
            playerEnemyInteraction.notifySubscribersCollision(player);
            notifyPropertyChanged(BR.healthText);
        }
    }



    public void spawnEnemies(int room) {
        switch (room) {
        case 0:
            enemy1 = createEnemy(EnemyType.Textbook, 595, 200);
            enemy2 = createEnemy(EnemyType.TA, 1165, 530);
            break;
        case 1:
            enemy1 = createEnemy(EnemyType.TA, 1510, 215);
            enemy2 = createEnemy(EnemyType.Professor, 1885, 710);
            break;
        case 2:
            enemy1 = createEnemy(EnemyType.Professor, 775, 560);
            enemy2 = createEnemy(EnemyType.HeadTA, 1675, 365);
            break;
        case 3:
            enemy1 = createEnemy(EnemyType.HeadTA, 1780, 215);
            enemy2 = createEnemy(EnemyType.Textbook, 1780, 710);
            break;
        default:
            return;
        }
        enemy1.setMoveStrategy(moveStrategy);
        enemy2.setMoveStrategy(moveStrategy);
        try {
            enemies.set(0, enemy1);
            enemies.set(1, enemy2);
        } catch (Exception e) {
            enemies.add(enemy1);
            enemies.add(enemy2);
        }
    }

    private Enemy createEnemy(EnemyType type, float x, float y) {
        Enemy enemy = EnemyFactory.createEnemy(type, config.getDifficulty().ordinal());
        enemy.setX(x);
        enemy.setY(y);
        return enemy;
    }

    public void spawnPowerUps(int room) {
        switch (room) {
        case 0:
            p1 = new DamageDecorator(new BasePowerUp(R.drawable.dmgpowerup, 850, 500));
            p2 = new HealthDecorator(new BasePowerUp(R.drawable.hppowerup, 1800, 660));
            break;
        case 1:
            p1 = new SpeedDecorator(new BasePowerUp(R.drawable.speedpowerup, 550, 600));
            p2 = new DurabilityDecorator(new BasePowerUp(R.drawable.durapowerup, 400, 400));
            break;
        case 2:
            p1 = new DamageDecorator(new BasePowerUp(R.drawable.dmgpowerup, 550, 600));
            p2 = new SpeedDecorator(new BasePowerUp(R.drawable.speedpowerup, 1800, 660));
            break;
        case 3:
            p1 = new HealthDecorator(new BasePowerUp(R.drawable.hppowerup, 550, 600));
            p2 = new DurabilityDecorator(new BasePowerUp(R.drawable.durapowerup, 1800, 660));
            break;
        default:
            return;
        }

    }
    public void endGame() {
        timer.cancel();
        endAction.postValue(state);
        player.resetInventory();
    }

    public void gameOver() {
        timer.cancel();
        gameOverAction.postValue(state);
    }

    public void startPlayerPosition() {
        player.setX((float) 970);
        player.setY((float) 470);
    }

    public void interact(int room, int x, int y, Player p) {
        if (room == 2 && x == 11 && y == 2) {
            endGame();
        }
    }

    public void checkPlayerHealth(Player player) {
        if (player.getCurrentHealth() <= 0) {
            gameOver();
        }
    }

    public void attack(int keyCode, List<Enemy> enemyList, boolean isTrue) {
        if (keyCode == KeyEvent.KEYCODE_S  && player.getWeapon() != null) {
            playerEnemyInteraction.notifySubscribersAttack(player);
            startAttack();
            if (isTrue) {
                new Handler().postDelayed(() -> endAttack(), 250);
            }
            if (enemyList.get(0).getCurrentHealth() == 0 && enemyList.get(0).getX() != -2000) {
                enemy1.setX(-2000);
                enemy1.setY(-2000);
                enemy1.setSpeed(0);
                state.setScore(state.getScore() + 1);
                enemies.set(0, enemy1);
            }
            if (enemyList.get(1).getCurrentHealth() == 0 && enemyList.get(1).getX() != -2000) {
                enemy2.setX(-2000);
                enemy2.setY(-2000);
                enemy2.setSpeed(0);
                state.setScore(state.getScore() + 1);
                enemies.set(1, enemy2);
            }
        }
        notifyPropertyChanged(BR.score);
    }

    public void startAttack() {
        isAttacking = true;
        attackTime = 25; // Adjust the time as needed for a quarter of a second
        notifyPropertyChanged(BR.attackVisibility);
    }

    public void endAttack() {
        isAttacking = false;
        notifyPropertyChanged(BR.attackVisibility);
    }

    public void toggleAttackVisibility() {
        if (attackVisibility == View.INVISIBLE) {
            attackVisibility = View.VISIBLE;
        } else {
            attackVisibility = View.INVISIBLE;
        }
        attackTime = 100;
        notifyPropertyChanged(BR.attackVisibility);
    }

    public void keyPressed(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            attack(keyCode, enemies, true);
            float oldX = player.getX();
            float oldY = player.getY();
            int oldRoom = map.getCurrentRoomNumber();
            Set<Coord> oldInts = player.getIntersections();
            switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                player.move(-player.getSpeed(), 0);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                player.move(player.getSpeed(), 0);
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                player.move(0, -player.getSpeed());
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                player.move(0, player.getSpeed());
                break;
            default:
                break;
            }

            Set<Coord> newInts = player.getIntersections();
            newInts.removeAll(oldInts);
            int room = map.getCurrentRoomNumber();
            for (Coord i : newInts) {
                int x = i.getX();
                int y = i.getY();
                Tile t = map.getCurrentRoom().getTile(x, y);
                if (t != null && t.isSolid()) {
                    // reject movement
                    player.setX(oldX);
                    player.setY(oldY);
                    return;
                }
                tileInteraction.notifySubscribers(room, x, y, player);
                playerEnemyInteraction.notifySubscribersCollision(player);
                notifyPropertyChanged(BR.healthText);
                playerPowerUpInteraction.notifySubscribersCollision();
                notifyPropertyChanged(BR.powerUp1Sprite);
                notifyPropertyChanged(BR.powerUp2Sprite);
                notifyPropertyChanged(BR.powerUp1X);
                notifyPropertyChanged(BR.powerUp1Y);
                notifyPropertyChanged(BR.powerUp2X);
                notifyPropertyChanged(BR.powerUp2Y);
            }



            if (map.getCurrentRoomNumber() != oldRoom) {
                playerEnemyInteraction.unsubscribe(enemy1);
                playerEnemyInteraction.unsubscribe(enemy2);
                playerPowerUpInteraction.unsubscribe(p1);
                playerPowerUpInteraction.unsubscribe(p2);
                startPlayerPosition();
                spawnEnemies(map.getCurrentRoomNumber());
                spawnWeapon(map.getCurrentRoomNumber());
                playerEnemyInteraction.subscribe(enemy1);
                playerEnemyInteraction.subscribe(enemy2);
                spawnPowerUps(map.getCurrentRoomNumber());
                playerPowerUpInteraction.subscribe(p1);
                playerPowerUpInteraction.subscribe(p2);
                notifyPropertyChanged(BR.enemy1Sprite);
                notifyPropertyChanged(BR.enemy2Sprite);
                notifyPropertyChanged(BR.enemy1X);
                notifyPropertyChanged(BR.enemy1Y);
                notifyPropertyChanged(BR.enemy2X);
                notifyPropertyChanged(BR.enemy2Y);
                notifyPropertyChanged(BR.powerUp1Sprite);
                notifyPropertyChanged(BR.powerUp2Sprite);
                notifyPropertyChanged(BR.powerUp1X);
                notifyPropertyChanged(BR.powerUp1Y);
                notifyPropertyChanged(BR.powerUp2X);
                notifyPropertyChanged(BR.powerUp2Y);
                notifyPropertyChanged(BR.map);
            }
            notifyPropertyChanged(BR.translateX);
            notifyPropertyChanged(BR.translateY);
        }
    }

    @Bindable
    public float getTranslateX() {
        return player.getX();
    }

    @Bindable
    public float getTranslateY() {
        return player.getY();
    }

    @Bindable
    public float getWeaponX() {
        return weapon.getXPos();
    }
    @Bindable
    public float getWeaponY() {
        return weapon.getYPos();
    }
    @Bindable
    public int getWeaponSprite() {
        return weapon.getSprite();
    }

    @Bindable
    public int getAttackSprite() {
        return R.drawable.attack;
    }

    @Bindable
    public int getAttackVisibility() {
        return isAttacking ? View.VISIBLE : View.INVISIBLE;
    }

    public Enemy getEnemy1() {
        return enemy1;
    }
    public Enemy getEnemy2() {
        return enemy2;
    }
    public List<Enemy> getEnemies() {
        return enemies;
    }
    @Bindable
    public float getEnemy1X() {
        return enemy1.getX();
    }

    @Bindable
    public float getEnemy1Y() {
        return enemy1.getY();
    }

    @Bindable
    public float getEnemy2X() {
        return enemy2.getX();
    }

    @Bindable
    public float getEnemy2Y() {
        return enemy2.getY();
    }

    @Bindable
    public String getEnemy1HP() {
        return String.format("%d/%d", enemy1.getCurrentHealth(), enemy1.getMaxHealth());
    }

    @Bindable
    public String getEnemy2HP() {
        return String.format("%d/%d", enemy2.getCurrentHealth(), enemy2.getMaxHealth());
    }

    @Bindable
    public float getPowerUp1X() {
        return p1.getX();
    }

    @Bindable
    public float getPowerUp1Y() {
        return p1.getY();
    }

    @Bindable
    public float getPowerUp2X() {
        return p2.getX();
    }

    @Bindable
    public float getPowerUp2Y() {
        return p2.getY();
    }

    @Bindable
    public String getPlayerName() {
        return config.getPlayerName();
    }

    @Bindable
    public int getSpriteResource() {
        Weapon weapon = player.getWeapon();
        String weaponName = weapon == null ? "none" : weapon.getName();
        return SPRITE_MAP.get(config.getSprite()).get(weaponName);
    }

    @Bindable
    public int getEnemy1Sprite() {
        return enemy1.getSprite();
    }

    @Bindable
    public int getEnemy2Sprite() {
        return enemy2.getSprite();
    }
    @Bindable
    public int getPowerUp1Sprite() {
        return p1.getSprite();
    }

    @Bindable
    public int getPowerUp2Sprite() {
        return p2.getSprite();
    }

    @Bindable
    public String getDifficulty() {
        return config.getDifficulty().toString();
    }

    @Bindable
    public String getAttempt() {
        return Integer.toString(state.getAttempt());
    }

    @Bindable
    public String getScore() {
        return Integer.toString(state.getScore());
    }

    @Bindable
    public String getTimerText() {
        return state.getElapsedTime().toString();
    }

    @Bindable
    public String getHealthText() {
        return String.format("%d/%d", player.getCurrentHealth(), player.getMaxHealth());
    }
    @Bindable
    public String getHungerText() {
        return String.format("%d/%d", player.getHunger(), player.getMaxHunger());
    }

    @Bindable
    public GameMap getMap() {
        return map;
    }

    @Bindable
    public int getItem1Sprite() {
        Item i = player.getInventory().getItem(0);
        return i == null ? 0 : i.getSprite();
    }

    @Bindable
    public int getItem2Sprite() {
        Item i = player.getInventory().getItem(1);
        return i == null ? 0 : i.getSprite();
    }

    @Bindable
    public int getItem3Sprite() {
        Item i = player.getInventory().getItem(2);
        return i == null ? 0 : i.getSprite();
    }

    @Bindable
    public int getItem4Sprite() {
        Item i = player.getInventory().getItem(3);
        return i == null ? 0 : i.getSprite();
    }

    @Bindable
    public int getItem5Sprite() {
        Item i = player.getInventory().getItem(4);
        return i == null ? 0 : i.getSprite();
    }
}
