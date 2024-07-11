package model.tiles.units;

import control.BoardGame;
import model.tiles.Empty;
import model.tiles.Tile;
import model.tiles.Wall;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import model.utils.Health;
import model.utils.Position;
import model.utils.generators.Generator;

public abstract class Unit extends Tile {
    protected String name;
    protected Health health;
    protected int attack;
    protected int defense;
    protected BoardGame board;
    protected Generator generator;

    public Unit(char tile, String name, int hitPoints, int attack, int defense,BoardGame board) {
        super(tile);
        this.name = name;
        this.health = new Health(hitPoints);
        this.attack = attack;
        this.defense = defense;
        this.board= board;
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
        target.takeDamage(damage - defense,this);

    }

    public int defend(){
        return generator.generate(defense);
    }

    public boolean alive(){
        return health.getCurrent() > 0;
    }

    public int takeDamage(int damage, Unit dealer){
        int life=health.takeDamage(damage);
        dealer.kill(this);
        return life;
    }
    public void combatBattle(Unit enemy) {
        attackOther(enemy,attack());
        if(!enemy.alive()){
            this.swapPosition(enemy);
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
        Tile tile= board.getTile(updatedPosition);
        interact(tile);
    }
    //TODO: move to heroic unit to abstract
    public void SpecialAbility(){
        return;
    }




}
