package Ui;

import model.tiles.Tile;
import model.utils.Position;

import java.util.TreeMap;

public class BoardView {
    private  String[][] boardGame;
    public  BoardView(TreeMap<Position, Tile> tiles ){
        for (Tile tile : tiles.values()) {
            Position p = tile.getPosition();
            boardGame[p.getX()][p.getY()]=tile.toString();
        }
    }
    public void printBoard(){
        for (String[] row : boardGame) {
            for (String s : row) {
                System.out.print(s);
            }
            System.out.println();
        }
    }
}
