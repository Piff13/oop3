package control;

import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;

import java.lang.module.FindException;
import java.util.List;

public class BoardController {
    private  BoardGame[] boardGames;
    private  int index;
    private Player currentPlayer;
    public BoardController() {
        boardGames = new BoardGame[3];
        index = 0;
    }
    public void GameStart(){

        while (index<3 && currentPlayer.alive()){
            BoardGame board= boardGames[index];
            board.PlayGame();
            index++;
        }
    }
}
