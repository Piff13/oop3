package control;

import Ui.BoardView;
import model.tiles.Empty;
import model.tiles.Tile;
import model.tiles.units.Unit;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import model.utils.Position;
import model.utils.callbacks.MessageCallback;

import java.awt.*;
import java.sql.ClientInfoStatus;
import java.util.*;
import java.util.List;

public class BoardGame {
    public TreeMap<Position,Tile> tiles;
    public List<Enemy> enemies;
    public Player player;
    public MessageCallback callBack;

    public Tile getTile(Position p)
    {
        return tiles.get(p);
    }
    public  BoardGame(TreeMap<Position,Tile> tiles, List<Enemy> enemies, MessageCallback callback){
       this.tiles= tiles;
       this.enemies = enemies;
       this.callBack = callback;
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
            Iterator<Enemy> iter = enemies.iterator();
            while(player.alive() && iter.hasNext()){
                iter.next().OnTick();
            }
            callBack.send(player.toString());
            BoardView board = new BoardView(tiles);
            board.printBoard();
        }
    }
    public  void chooseAct(char act){
        Position p =player.getPosition();
        int x= p.getX();
        int y= p.getY();
        boolean found = false;
        while(!found){
            if(act == 'w'){
                player.move(x,y+1);
                found = true;
            }
            if(act == 's'){
                player.move(x,y-1);
                found = true;
            }
            if(act == 'a'){
                player.move(x-1,y);
                found = true;
            }
            if(act == 'd'){
                player.move(x+1,y);
                found = true;
            }
            if(act == 'e'){
                player.SpecialAbility();
                found = true;
            }
            if(act == 'q'){
                found = true;
            } else{
                callBack.send("illegal char");
            }
        }
        player.OnTick();
    }

}