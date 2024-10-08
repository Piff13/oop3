package model.tiles.units.players;

import control.BoardGame;
import model.tiles.units.HeroicUnit;
import model.tiles.units.Unit;
import model.tiles.units.enemies.Enemy;
import model.utils.BoardHelper;
import model.utils.Position;
import model.utils.callbacks.MessageCallback;

import java.util.LinkedList;
import java.util.List;

public abstract class Player extends Unit implements HeroicUnit {
    protected static final char PLAYER_TILE = '@';
    protected static final int LEVEL_REQUIREMENT = 50;
    protected static final int HEALTH_GAIN = 10;
    protected static final int ATTACK_GAIN = 4;
    protected static final int DEFENSE_GAIN = 1;
    protected int level;
    protected int experience;

    public Player(String name, int hitPoints, int attack, int defense, BoardHelper boardHelper, MessageCallback callback) {
        super(PLAYER_TILE, name, hitPoints, attack, defense, boardHelper, callback);
        this.level = 1;
        this.experience = 0;
    }

    public void addExperience(int experienceValue){
        this.experience += experienceValue;
        while (experience >= levelRequirement()) {
            levelUp();
        }
    }

    public void levelUp(){
        this.experience -= levelRequirement();
        this.level++;
        int healthGain = healthGain();
        int attackGain = attackGain();
        int defenseGain = defenseGain();
        health.increaseMax(healthGain);
        health.heal();
        attack += attackGain;
        defense += defenseGain;
        callBack.send("you leveled up!\n" + printLevelUpStats());
    }
    protected String printLevelUpStats(){
        String str = "new level is: " + level + " you gained: " + healthGain() + " hp, ";
        str += attackGain() + " attack, " + defenseGain() + " defense\n";
        return str;
    }

    protected int levelRequirement(){
        return LEVEL_REQUIREMENT * level;
    }

    protected int healthGain(){
        return HEALTH_GAIN * level;
    }

    protected int attackGain(){
        return ATTACK_GAIN * level;
    }

    protected int defenseGain(){
        return DEFENSE_GAIN * level;
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    @Override
    public void visit(Player p){
        // Do nothing
    }

    public void visit(Enemy e){
        combatBattle(e);
    }
    public void kill(Unit unit){
        unit.onDeath(this);
    }

    @Override
    public void onDeath(Enemy e) {
        this.setTile('X');
        callBack.send(this.getName() + "was killed by " +  e.getName() + "\n");
    }
    public void onDeath(Player p){
        return;//no friendly fire
    }
    public List<Enemy> getPotentialTargets(int range){
        List<Enemy> result = new LinkedList<Enemy>();
        for(Enemy enemy: boardHelper.getEnemies()){
            if(position.range(enemy.getPosition()) < range)
                result.add(enemy);
        }
        return result;
    }

    public void OnTick(){
        updateDelay();
    }
    public abstract void updateDelay();
    public abstract void SpecialAbility();
    public void castAbility(){
        SpecialAbility();
    }

    public String toString() {
        return super.toString() + " ,level: " + level + " ,xp: " + experience + "/" + levelRequirement();
    }
    public void attackOtherAbility(Unit target, int damage){
        int defense = target.defend();
        callBack.send(target.getName() + " has rolled " + defense + " defense points\n");
        int damageTaken = Math.max(damage - defense, 0);
        callBack.send((this.getName() + " hit " + target.getName() + " for " +  damageTaken + " ability damage\n"));
        target.takeDamage(damageTaken,this);
    }


}
