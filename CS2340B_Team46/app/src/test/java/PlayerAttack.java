import static org.junit.Assert.assertTrue;

import android.view.KeyEvent;

import com.example.cs2340.R;
import com.example.cs2340.model.GameConfiguration;
import com.example.cs2340.model.GameDifficulty;
import com.example.cs2340.model.entity.Player;
import com.example.cs2340.model.inventory.Weapon;
import com.example.cs2340.viewmodel.GameViewModel;

import org.junit.Test;

public class PlayerAttack {
    @Test
    public void attackInRangeTest() {
        GameConfiguration config = new GameConfiguration("test", GameDifficulty.EASY, null);
        GameViewModel gvm = new GameViewModel(1, config);
        gvm.getEnemy1().setX(0);
        gvm.getEnemy1().setY(0);

        Player player = Player.getInstance();
        String expectedHP = String.format("%d/%d", 0, gvm.getEnemy1().getMaxHealth());
        player.setWeapon(new Weapon("sword", 0, R.drawable.sword, 1, 1000));
        gvm.attack(KeyEvent.KEYCODE_S, gvm.getEnemies(), false);
        assertTrue(gvm.getEnemy1HP().equals(expectedHP));
    }

    @Test
    public void attackNotInRangeTest() {
        GameConfiguration config = new GameConfiguration("test", GameDifficulty.EASY, null);
        GameViewModel gvm = new GameViewModel(1, config);
        gvm.getEnemy1().setX(1000);
        gvm.getEnemy1().setY(1000);

        Player player = Player.getInstance();
        String expectedHP = String.format("%d/%d",
                gvm.getEnemy1().getMaxHealth(), gvm.getEnemy1().getMaxHealth());
        player.setWeapon(new Weapon("sword", 0, R.drawable.sword, 1, 1000));
        gvm.attack(KeyEvent.KEYCODE_S, gvm.getEnemies(), false);
        assertTrue(gvm.getEnemy1HP().equals(expectedHP));
    }

    @Test
    public void scoreChangedTest() {
        GameConfiguration config = new GameConfiguration("test", GameDifficulty.EASY, null);
        GameViewModel gvm = new GameViewModel(1, config);
        gvm.getEnemy1().setX(0);
        gvm.getEnemy1().setY(0);

        Player player = Player.getInstance();
        player.setWeapon(new Weapon("sword", 0, R.drawable.sword, 1, 1000));
        gvm.attack(KeyEvent.KEYCODE_S, gvm.getEnemies(), false);
        assertTrue(Integer.parseInt(gvm.getScore()) == 1);
    }
}
