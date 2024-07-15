import control.BoardController;
import control.BoardGame;
import model.tiles.Tile;
import model.tiles.units.players.Player;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        BoardController bc = new BoardController();
        bc.GameStart();
    }
}