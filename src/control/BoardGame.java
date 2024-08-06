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
    public List<Tile> tiles;
    public List<Enemy> enemies;
    public Player player;
    public int boardWidth;
    public int boardHeight;
    public MessageCallback callBack;

    public BoardGame() {

    }

    public Tile getTile(Position p)
    {
        Tile tile=null;
        for(Tile t:tiles){
            Position p1 =t.getPosition();
            if(p1.getX()==p.getX()&& p1.getY()== p.getY()){
                tile=t;
            }

        }
        return  tile;
    }
    public BoardGame(List<Tile> tiles, List<Enemy> enemies, MessageCallback callback, int boardHeight, int boardWidth, Player p){
       this.tiles= tiles;
       this.enemies = enemies;
       this.callBack = callback;
       this.boardWidth = boardWidth;
       this.boardHeight = boardHeight;
       this.player = p;
    }
    public List<Enemy> getEnemies (){
        return enemies;
    }
    public void removeEnemy(Enemy e){
        Tile t= new Empty();
        t.initialize(e.getPosition());
        tiles.add(t);
        tiles.remove(e);
        enemies.remove(e);
    }
    public  boolean IsAllDeath(){
        return  0 == enemies.size();
    }
    public  void PlayGame(){
        BoardView board = new BoardView(tiles,boardHeight, boardWidth);
        board.printBoard();//first print before moves
        while(player.alive() && !IsAllDeath()){
            callBack.send(player.toString());
            chooseAct();
            Iterator<Enemy> iter = enemies.iterator();
            while(player.alive() && iter.hasNext()){
                iter.next().OnTick();
            }
            player.OnTick();
            board = new BoardView(tiles,boardHeight, boardWidth);
            board.printBoard();
        }
    }
    public  void chooseAct(){
        Scanner sc = new Scanner(System.in);
        System.out.println("choose w to move up , s down , a left, d right, e special ability and q nothing\n");
        char act= sc.next().charAt(0);
        Position p =player.getPosition();
        int x= p.getX();
        int y= p.getY();
        boolean found = false;
        while(!found){
            if(act == 'w'){
                player.move(x,y-1);
                found = true;
            }
            else if(act == 's'){
                player.move(x,y+1);
                found = true;
            }
            else if(act == 'a'){
                player.move(x-1,y);
                found = true;
            }
            else if(act == 'd'){
                player.move(x+1,y);
                found = true;
            }
            else if(act == 'e'){
                player.SpecialAbility();
                found = true;
            }
            else if(act == 'q'){
                found = true;
            } else{
                callBack.send("illegal char, choose other\n");
                act = sc.next().charAt(0);
            }
        }
    }

}