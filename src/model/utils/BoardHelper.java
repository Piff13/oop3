package model.utils;

import control.BoardGame;
import model.tiles.Tile;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;

import java.util.List;

public class BoardHelper {
    private BoardGame board;
    public BoardHelper(BoardGame board){
        this.board = board;
    }
    public void setBoard(BoardGame board){
        this.board = board;
    }
    public Tile getTile(Position p)
    {
        return board.getTile(p);
    }
    public List<Enemy> getEnemies (){
        return board.getEnemies();
    }
    public void removeEnemy(Enemy e){
        board.removeEnemy(e);
    }
    public Position getPlayerPosition(){
        return board.player.getPosition();
    }
    public Player getPlayer(){
        return board.player;
    }
}
