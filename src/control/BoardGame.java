package control;

import Ui.BoardView;
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
    public void addEmpty(Position p){
        tiles.put(p, new Empty());
    }
    public  boolean IsAllDeath(){
        return  0 == enemies.size();
    }
    public  void PlayGame(){
        Scanner sc = new Scanner(System.in);
        while(player.alive() && !IsAllDeath()){
            char act= sc.next().charAt(0);
            chooseAct(act);
            for(Enemy enemy : enemies){
                enemy.OnTick();
            }
            BoardView board = new BoardView(tiles);
            board.printBoard();
        }
    }
    public  void chooseAct(char act){
        Position p =player.getPosition();
        int x= p.getX();
        int y= p.getY();
        if(act == 'w'){
            player.move(x,y+1);
        }
        if(act == 's'){
            player.move(x,y-1);
        }
        if(act == 'a'){
            player.move(x-1,y);
        }
        if(act == 'd'){
            player.move(x+1,y);
        }
        if(act == 'e'){
            player.SpecialAbility();
        }
        if(act == 'q'){

        }
    }

}