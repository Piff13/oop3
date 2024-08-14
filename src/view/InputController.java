package view;
import control.BoardGame;
import model.tiles.Tile;
import model.tiles.units.Characters;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.enemies.Monster;
import model.tiles.units.enemies.Trap;
import model.tiles.units.players.Player;
import model.utils.BoardHelper;
import model.utils.Position;
import model.utils.callbacks.MessageCallback;
import model.tiles.*;
import model.utils.generators.Generator;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

import static java.util.Map.entry;

public class InputController {
    private MessageCallback messageCallback;
    private BoardHelper boardHelper;
    private Characters characterController;
    private Map<Character, Tile> mapOfTiles;
    private Map<Character,? extends Enemy> mapOfEnemies;
    private Map<Integer, Player> mapOfPlayers;
    public InputController(MessageCallback messageCallback, BoardHelper boardHelper){
        this.messageCallback = messageCallback;
        this.boardHelper = boardHelper;
        characterController = new Characters(messageCallback, boardHelper);
        this.initMaps();
    }
    private void initMaps(){
        mapOfTiles = Map.ofEntries(
                entry('.', new Empty()),
                entry('#', new Wall())
        );

        mapOfEnemies = Map.ofEntries(
                entry('s',  characterController.Create_Lannister_Solider()),
                entry('k',  characterController.Create_Lannister_Knight()),
                entry('q',  characterController.Create_Queens_Guard()),
                entry('z',  characterController.Create_Wright()),
                entry('b',  characterController.Create_Bear_Wright()),
                entry('g',  characterController.Create_Giant_Wright()),
                entry('w',  characterController.Create_White_Walker()),
                entry('M',  characterController.Create_The_Mountain()),
                entry('C',  characterController.Create_Queen_Cersei()),
                entry('K',  characterController.Create_Nights_King()),
                entry('B',  characterController.Create_Bonus_Trap()),
                entry('Q',  characterController.Create_Queens_Trap()),
                entry('D', characterController.Create_Death_Trap())
        );

        mapOfPlayers = Map.ofEntries(
                entry(1, characterController.Create_Jon_Snow()),
                entry(2, characterController.Create_The_Hound()),
                entry(3, characterController.Create_Melisandre()),
                entry(4, characterController.Create_Thoros_of_Myr()),
                entry(5, characterController.Create_Arya_Stark()),
                entry(6, characterController.Create_Bronn()),
                entry(7, characterController.Create_Ygritte())
        );
    }
    private Tile createTile(Tile t){
        return t.getCopy();
    }
    private Enemy createTile(Enemy e){
        return e.getCopy();
    }
    public BoardGame CreateBoardFromFile(String filePath, Player player, Generator generator){
        List<Tile> tiles = new LinkedList<>();
        List<Enemy> enemies = new LinkedList<Enemy>();
        int y = 0;//will also help to determine board height
        int width = 0;
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                width = data.length();
                for(int x = 0; x < data.length(); x++){
                    char currenctChar = data.charAt(x);
                    Tile currentTile;
                    Position p = new Position(x, y);
                    if(mapOfTiles.get(currenctChar) != null) {
                        currentTile = createTile(mapOfTiles.get(currenctChar));
                        currentTile.initialize(p);
                    } else if(mapOfEnemies.get(currenctChar) != null){
                        Enemy e = createTile(mapOfEnemies.get(currenctChar));
                        e.initialize(p, generator);
                        enemies.add(e);
                        currentTile = e;
                    } else {//only remaining option is player - @
                        currentTile = player;
                        player.initialize(p, generator);
                    }
                    tiles.add(currentTile);
                }
                y++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.\n");
            e.printStackTrace();
        }
        return new BoardGame(tiles, enemies, messageCallback, y,width, player);
    }


    public Player CreatePlayer(){
        Scanner scan = new Scanner(System.in);
        System.out.println("choose player");
        System.out.println("1 - " + mapOfPlayers.get(1));
        System.out.println("2 - " + mapOfPlayers.get(2));
        System.out.println("3 - " + mapOfPlayers.get(3));
        System.out.println("4 - " + mapOfPlayers.get(4));
        System.out.println("5 - " + mapOfPlayers.get(5));
        System.out.println("6 - " + mapOfPlayers.get(6));
        System.out.println("7 - " + mapOfPlayers.get(7));
        int index = scan.nextInt();
        while(!(index >= 1 & index <= 7))//legal bound
            index = scan.nextInt();
        return mapOfPlayers.get(index);
    }
    public Player CreatePlayer(int i ){
        Scanner scan = new Scanner(System.in);
        while(!(i >= 1 & i <= 7))//legal bound
            i = scan.nextInt();
        return mapOfPlayers.get(i);
    }
    public  Tile createEmpty(){
        return mapOfTiles.get('.');
    }
    public BoardGame createBoardEmpty(Player player,Position p , Position p1){
        List<Tile> tiles = new LinkedList<>();
        List<Enemy> enemies = new LinkedList<Enemy>();
        Tile e= mapOfTiles.get('.');
        e.initialize(p1);
        player.initialize(p);
        tiles.add(e);
        tiles.add(player);
        return new BoardGame(tiles, enemies, messageCallback, 100,100, player);
    }
    public BoardGame createBoardWall(Player player,Position p , Position p1){
        List<Tile> tiles = new LinkedList<>();
        List<Enemy> enemies = new LinkedList<Enemy>();
        Tile e= mapOfTiles.get('#');
        e.initialize(p1);
        player.initialize(p);
        tiles.add(e);
        tiles.add(player);
        return new BoardGame(tiles, enemies, messageCallback, 100,100, player);
    }
    public BoardGame createBoardEnemy(Player player,Position p , Position p1,Generator g){
        List<Tile> tiles = new LinkedList<>();
        List<Enemy> enemies = new LinkedList<Enemy>();
        Enemy e= mapOfEnemies.get('s');
        e.initialize(p1,g);
        player.initialize(p,g);
        tiles.add(e);
        tiles.add(player);
        enemies.add(e);
        return new BoardGame(tiles, enemies, messageCallback, 100,100, player);
    }
    public BoardGame createBoardEnemyWall(Player player,Position p , Position p1,Generator g,Position p2){
        List<Tile> tiles = new LinkedList<>();
        List<Enemy> enemies = new LinkedList<Enemy>();
        Enemy e= mapOfEnemies.get('s');
        e.initialize(p1,g);
        Tile e1= mapOfTiles.get('#');
        e1.initialize(p2);
        player.initialize(p,g);
        tiles.add(e);
        tiles.add(player);
        tiles.add(e1);
        enemies.add(e);
        return new BoardGame(tiles, enemies, messageCallback, 100,100, player);
    }
    public BoardGame createBoardEnemyEmpty(Player player,Position p , Position p1,Generator g,Position p2){
        List<Tile> tiles = new LinkedList<>();
        List<Enemy> enemies = new LinkedList<Enemy>();
        Enemy e= mapOfEnemies.get('s');
        e.initialize(p1,g);
        Tile e1= mapOfTiles.get('.');
        e1.initialize(p2);
        player.initialize(p,g);
        tiles.add(e);
        tiles.add(player);
        tiles.add(e1);
        enemies.add(e);
        return new BoardGame(tiles, enemies, messageCallback, 100,100, player);
    }
    public BoardGame createBoardEnemyWall1(Player player,Position p , Position p1,Generator g,Position p2){
        List<Tile> tiles = new LinkedList<>();
        List<Enemy> enemies = new LinkedList<Enemy>();
        Enemy e= mapOfEnemies.get('s');
        e.initialize(p1,g);
        Tile e1= mapOfTiles.get('#');
        e1.initialize(p2);
        player.initialize(p,g);
        tiles.add(e);
        tiles.add(e1);
        tiles.add(player);
        enemies.add(e);
        return new BoardGame(tiles, enemies, messageCallback, 100,100, player);
    }
    public BoardGame createBoardEnemyEnemy(Player player,Position p , Position p1,Generator g,Position p2,Position p3){
        List<Tile> tiles = new LinkedList<>();
        List<Enemy> enemies = new LinkedList<Enemy>();
        Enemy e= mapOfEnemies.get('s');
        e.initialize(p1,g);
        Enemy e11= mapOfEnemies.get('s');
        e11.initialize(p3,g);
        Tile e1= mapOfTiles.get('#');
        e1.initialize(p2);
        player.initialize(p,g);
        tiles.add(e);
        tiles.add(e11);
        tiles.add(e1);
        tiles.add(player);
        enemies.add(e);
        enemies.add(e11);
        return new BoardGame(tiles, enemies, messageCallback, 100,100, player);
    }
    public BoardGame createBoardEnemyTrap(Player player,Position p , Position p1,Generator g){
        List<Tile> tiles = new LinkedList<>();
        List<Enemy> enemies = new LinkedList<Enemy>();
        Enemy e= mapOfEnemies.get('Q');
        e.initialize(p1,g);
        player.initialize(p,g);
        tiles.add(e);
        tiles.add(player);
        enemies.add(e);
        return new BoardGame(tiles, enemies, messageCallback, 100,100, player);
    }
    public BoardGame createBoardEnemyTrapMonster(Player player,Position p , Position p1,Generator g,Position p2){
        List<Tile> tiles = new LinkedList<>();
        List<Enemy> enemies = new LinkedList<Enemy>();
        Enemy e= mapOfEnemies.get('Q');
        Enemy e1= mapOfEnemies.get('s');
        e.initialize(p1,g);
        e1.initialize(p2,g);
        player.initialize(p,g);
        tiles.add(e);
        tiles.add(e1);
        tiles.add(player);
        enemies.add(e);
        enemies.add(e1);
        return new BoardGame(tiles, enemies, messageCallback, 100,100, player);
    }
    public  void setboard(BoardGame board){
        boardHelper.setBoard(board);
    }
}
