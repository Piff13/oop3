package control;

import model.tiles.Empty;
import model.tiles.Tile;
import model.tiles.units.Unit;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import model.utils.Position;

import java.awt.*;
import java.sql.ClientInfoStatus;
import java.util.*;
import java.util.List;

public class BoardGame {
    public TreeMap<Position,Tile> tiles;
    public List<Enemy> enemies;
    public Player player;

    public Tile getTile(Position p)
    {
        return tiles.get(p);
    }
    public  BoardGame(TreeMap<Position,Tile> tiles, List<Enemy> enemies){
       this.tiles= tiles;
       this.enemies = enemies;
    }
    public List<Enemy> getEnemies (){
        return enemies;
    }
    public void removeEnemy(Enemy e){
        tiles.remove(e.getPosition(),e);
        tiles.put(e.getPosition(), new Empty());
        enemies.remove(e);
    }
}
