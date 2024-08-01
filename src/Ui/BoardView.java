package Ui;

import model.tiles.Tile;
import model.utils.Position;

import java.util.List;
import java.util.TreeMap;

public class BoardView {
    private  char[][] boardGame;
    public BoardView(List< Tile> tiles, int height, int width){
        boardGame = new char[height][width];
        for (Tile tile : tiles) {
            Position p = tile.getPosition();
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
