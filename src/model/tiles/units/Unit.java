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
        int damage = generator.generate(attack);
        callBack.send(this.getName() + " rolled " + damage + " damage points\n");
        return damage;
    }
    public void attackOther(Unit target, int damage){
        int defense = target.defend();
        callBack.send(target.getName() + " rolled " + defense + " defense points\n");
        int damageTaken = Math.max(damage - defense, 0);
        callBack.send(this.getName() + " has dealt: " + damageTaken + " damage points to " + target.getName() + '\n');
        target.takeDamage(damageTaken,this);

    }

    public int defend(){
        return generator.generate(defense);
    }

    public boolean alive(){
        return health.getCurrent() > 0;
    }

    public void takeDamage(int damage, Unit dealer){
        health.takeDamage(damage);
        if(!alive())
            dealer.kill(this);
    }
    public void combatBattle(Unit enemy) {
        callBack.send(this.getName() + " engaged in melee combat with " + enemy.getName() + "\n");
        callBack.send(this.toString() + "\n");
        callBack.send((enemy.toString()) + "\n");
        int damage = attack();
        attackOther(enemy, damage);
        Position p=enemy.getPosition();
        if(!enemy.alive() & boardHelper.getPlayer().alive()){//don't switch if player died
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
    public String getName(){
        return name;
    }
    public Tile getCopy(){
        return null;
    }
    public  int getHealth(){
        return health.getCurrent();
    }
}
