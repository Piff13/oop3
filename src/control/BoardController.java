package control;

import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import model.utils.BoardHelper;
import model.utils.callbacks.MessageCallback;
import model.utils.generators.RandomGenerator;
import view.CLI;
import view.InputController;
import view.View;

import java.lang.module.FindException;
import java.util.List;

public class BoardController {
    private  int index;
    private InputController inputController;
    private RandomGenerator generator;
    private MessageCallback messageCallback;
    private BoardHelper boardHelper;
    private String[] filePaths;
    public BoardController() {
        index = 0;
        View view = new CLI();
        messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        boardHelper = new BoardHelper(board);
        inputController = new InputController(messageCallback, boardHelper);
        generator = new RandomGenerator();
        filePaths = new String[]{
                "C:\\levels_dir\\level1.txt",
                "C:\\levels_dir\\level2.txt",
                "C:\\levels_dir\\level3.txt",
                "C:\\levels_dir\\level4.txt"};

    }
    public void GameStart(){
        Player p = inputController.CreatePlayer();
        messageCallback.send("you have selected:\n" + p.getName());
        while (index<4 && p.alive()){
            BoardGame board= inputController.CreateBoardFromFile(filePaths[index],p,generator);
            boardHelper.setBoard(board);
            board.PlayGame();
            index++;
            if(p.alive() & index < 4)
                messageCallback.send("level finished, advancing to level " + (index + 1) + "\n");
        }
        if(p.alive())
            messageCallback.send("you won\n");
        else{
            messageCallback.send("you lost\n");
        }
    }
}
