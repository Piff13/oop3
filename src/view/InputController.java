package view;
import control.BoardGame;
import model.tiles.Tile;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.enemies.Monster;
import model.tiles.units.enemies.Trap;
import model.tiles.units.players.Player;
import model.utils.Position;
import model.utils.callbacks.MessageCallback;
import model.tiles.*;
import model.utils.generators.Generator;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

import static java.util.Map.entry;

public class InputController {
    private Map<Character, Monster> mapOfMonsters = Map.ofEntries(
            entry('s',  ),
            entry('k',  ),
            entry('q',  ),
            entry('z',  ),
            entry('b',  ),
            entry('g',  ),
            entry('w',  ),
            entry('M',  ),
            entry('C',  ),
            entry('K',  )

    );
    private Map<Character, Trap> mapOfTraps = Map.ofEntries(
            entry('B',  ),
            entry('Q',  ),
            entry('D', )

    );
    private Map<Character, Player> mapOfPlayers = Map.ofEntries(
            entry('D', )
    );
    public BoardGame CreateBoardFromFile(String filePath, MessageCallback callback, Player player, Generator generator){
        TreeMap<Position,Tile> tiles = new TreeMap<>();
        List<Enemy> enemies = new LinkedList<Enemy>();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            int y = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for(int x = 0; x < data.length(); x++){
                    char currenctChar = data.charAt(x);
                    Tile currentTile;
                    Position p = new Position(x, y);
                    if(currenctChar == '.') {
                        currentTile = new Empty();
                        currentTile.initialize(p);
                    } else if(currenctChar == '#'){
                        currentTile = new Wall();
                        currentTile.initialize(p);
                    } else if(mapOfMonsters.get(currenctChar) != null){
                        Enemy e = mapOfMonsters.get(currenctChar);
                        e.initialize(p, generator);
                        enemies.add(e);
                        currentTile =  e;
                    } else if(mapOfTraps.get(currenctChar) != null){
                        Enemy e = mapOfMonsters.get(currenctChar);
                        e.initialize(p, generator);
                        enemies.add(e);
                        currentTile = e;
                        mapOfTraps.get(currenctChar).initialize(p, generator);
                    } else {//only remaining option is player - @
                        currentTile = player;
                        player.initialize(p, generator);
                    }
                    tiles.put(p, currentTile);
                }
                y++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return new BoardGame(tiles, enemies, callback);
    }

    public Player CreatePlayer(){
        Scanner scan = new Scanner(System.in);
        System.out.println("choose player");
        return mapOfPlayers.get(scan.next().charAt(0));

    }
}
