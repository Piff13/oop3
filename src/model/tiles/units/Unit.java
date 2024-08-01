package model.tiles.units;

import control.BoardGame;
import model.tiles.Empty;
import model.tiles.Tile;
import model.tiles.Wall;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import model.utils.BoardHelper;
import model.utils.Health;
import model.utils.Position;
import model.utils.callbacks.MessageCallback;
import model.utils.generators.Generator;

public abstract class Unit extends Tile {
    protected MessageCallback callBack;
    protected String name;
    protected Health health;
    protected int attack;
    protected int defense;
    protected Generator generator;
    protected BoardHelper boardHelper;

    public Unit(char tile, String name, int hitPoints, int attack, int defense, BoardHelper boardHelper, MessageCallback callBack) {
        super(tile);
        this.name = name;
        this.health = new Health(hitPoints);
        this.attack = attack;
        this.defense = defense;
        this.callBack = callBack;
        this.boardHelper = boardHelper;
    }

    public void initialize(Position p, Generator generator){
        super.initialize(p);
        this.generator = generator;
    }

    public int attack(){
        return generator.generate(attack);
    }
    public void attackOther(Unit target,int damage){
        int defense = target.defend();
        callBack.send(this.toString() + " attacked with " + damage + " damage " + target.toString() +'\n');
        callBack.send(target.toString() + " rolled " + defense + " defense\n");
        target.takeDamage(Math.max(damage - defense, 0),this);

    }

    public int defend(){
        return generator.generate(defense);
    }

    public boolean alive(){
        return health.getCurrent() > 0;
    }

    public int takeDamage(int damage, Unit dealer){
        int life=health.takeDamage(damage);
        callBack.send(this.toString() + " took " + damage + " damage\n");
        if(!alive())
            dealer.kill(this);
        return life;
    }
    public void combatBattle(Unit enemy) {
        Position p=enemy.getPosition();
        attackOther(enemy,attack());
        if(!enemy.alive() & boardHelper.getPlayer().alive()){
            this.swapPosition(boardHelper.getTile(p));
        }

    }
    public void interact(Tile t){
        t.accept(this);
    }

    public void visit(Empty e){
        swapPosition(e);

    }

    public void visit(Wall w){
        // Do nothing
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);
    public void onDeath(Unit uni){
        return;
    }

    public abstract void onDeath(Player p);
    public abstract void onDeath(Enemy e);
    public abstract void kill(Unit uni);
    public  void move(int x, int y){
        Position updatedPosition=new Position(x,y);
        Tile tile= boardHelper.getTile(updatedPosition);
        interact(tile);
    }
    public abstract void OnTick();
    public String toString(){
        return "name: " + name + " ,hp: " + health + " ,attack: " + attack + " ,defense: " + defense;
    }


}
