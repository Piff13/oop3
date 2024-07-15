package Ui;

import model.tiles.Tile;
import model.utils.Position;

import java.util.TreeMap;

public class BoardView {
    private  char[][] boardGame;
    public BoardView(TreeMap<Position, Tile> tiles, int height, int width){
        boardGame = new char[height][width];
        for (Tile tile : tiles.values()) {
            Position p = tile.getPosition();
            if(tile.tile == 'k')
                System.out.println("x :" + tile.getPosition().getX() + " ,y: " + tile.getPosition().getY());
            boardGame[p.getY()][p.getX()]=tile.view();
        }
    }
    public void printBoard(){
        for (char[] row : boardGame) {
            for (char s : row) {
                System.out.print(s);
            }
            System.out.println();
        }
    }
}
