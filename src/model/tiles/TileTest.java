package model.tiles;

import control.BoardGame;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import model.tiles.units.players.warrior;
import model.utils.BoardHelper;
import model.utils.Position;
import model.utils.callbacks.MessageCallback;
import model.utils.generators.Generator;
import model.utils.generators.RandomGenerator;
import org.junit.Assert;
import org.junit.Test;
import view.CLI;
import view.InputController;
import view.View;

import java.util.List;

import static org.junit.Assert.*;

public class TileTest {
    @Test
    public void TileTestEmptyAndPlayer() {
        View view = new CLI();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEmpty(p,pos1,pos2);
        inputController.setboard(empty);
        p.move(1,2);
        Assert.assertEquals(p.getPosition().getY(),2);
    }
    @Test
    public void TileTestWallAndPlayer() {
        View view = new CLI();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardWall(p,pos1,pos2);
        inputController.setboard(empty);
        p.move(1,2);
        Assert.assertEquals(p.getPosition().getY(),1);
    }
    @Test
    public void TileTestEnemeisAndPlayer() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemy(p,pos1,pos2,generator);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        p.move(1,2);
        Assert.assertEquals(e.get(0).getHealth()<=80,true);
    }
    @Test
    public void TileTestEnemeisAndPlayer1() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemy(p,pos1,pos2,generator);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        e.get(0).move(1,1);
        Assert.assertEquals(p.getHealth()<=300,true);
    }
    @Test
    public void TileTestEnemeisAndPlayer2() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemy(p,pos1,pos2,generator);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        p.castAbility();
        Assert.assertEquals(e.get(0).getHealth()<80,true);
    }
    @Test
    public void TileTestEnemeisAndPlayer3() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemy(p,pos1,pos2,generator);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        p.castAbility();
        Assert.assertEquals(e.get(0).getHealth()>80,false);
    }
    @Test
    public void TileTestEnemeisAndPlayer4() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Position pos3 =new Position(1,3);

        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemyWall(p,pos1,pos2,generator,pos3);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        e.get(0).move(1,3);
        Assert.assertEquals(e.get(0).getPosition().getY(),2);
    }
    @Test
    public void TileTestEnemeisAndPlayer5() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Position pos3 =new Position(1,3);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemyEmpty(p,pos1,pos2,generator,pos3);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        e.get(0).move(1,3);
        Assert.assertEquals(e.get(0).getPosition().getY(),3);
    }
    @Test
    public void TileTestEnemeisAndPlayer6() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemy(p,pos1,pos2,generator);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        p.move(1,2);
        Assert.assertEquals(e.get(0).alive(),true);
    }
    @Test
    public void TileTestEnemeisAndPlayer7() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemy(p,pos1,pos2,generator);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        for(int i=0 ;i<1000;i++){
            e.get(0).move(1,1);
        }
        Assert.assertEquals(p.alive(),false);

    }
    @Test
    public void TileTestEnemeisAndPlayer8() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemy(p,pos1,pos2,generator);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        for(int i=0 ;i<1000;i++){
            e.get(0).move(1,1);
        }
        Assert.assertEquals(p.getTile(),'X');
    }
    @Test
    public void TileTestEnemeisAndPlayer9() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemy(p,pos1,pos2,generator);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        for(int i=0 ;i<1000;i++){
            p.move(1,2);
        }
        Assert.assertEquals(empty.getEnemies().isEmpty(),true);
    }
    @Test
    public void TileTestEnemeisAndPlayer10() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Position pos3 =new Position(1,3);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemyWall1(p,pos1,pos3,generator,pos2);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        p.move(1,2);
        e.get(0).move(1,2);
        Assert.assertEquals(empty.getEnemies().get(0).getPosition().getY(),3);
        Assert.assertEquals(p.getPosition().getY(),1);
    }
    @Test
    public void TileTestEnemeisAndPlayer11() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Position pos3 =new Position(1,3);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemyWall1(p,pos1,pos3,generator,pos2);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        p.castAbility();
        Assert.assertEquals(empty.getEnemies().get(0).getHealth()<=80,true);
    }

    @Test
    public void TileTestEnemeisAndMonster() {
        View view = new CLI();
        Generator generator = new RandomGenerator();
        MessageCallback messageCallback = view.getCallback();
        BoardGame board = new BoardGame();
        BoardHelper boardHelper = new BoardHelper(board);
        InputController inputController = new InputController(messageCallback, boardHelper);
        Position pos1 =new Position(1,1);
        Position pos2 =new Position(1,2);
        Position pos3 =new Position(1,3);
        Position pos4 =new Position(1,4);
        Player p=inputController.CreatePlayer(1);
        BoardGame empty=inputController.createBoardEnemyEnemy(p,pos1,pos3,generator,pos2,pos4);
        inputController.setboard(empty);
        List<Enemy> e=empty.getEnemies();
        e.get(0).move(1,4);
        Assert.assertEquals(empty.getEnemies().get(0).getPosition().getY(),4);
    }

}