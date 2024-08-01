package model.tiles.units.players;

import control.BoardGame;
import model.tiles.units.enemies.Enemy;
import model.utils.BoardHelper;
import model.utils.callbacks.MessageCallback;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class warrior extends  Player {
    protected  final int BONUS_HEALTH_WARRIOR = 5;
    protected final int BONUS_DEFENSE_WARRIOR = 1;
    protected final int BONUS_ATTACK_WARRIOR = 2;
    protected final int WARRIOR_COOLDOWN;
    protected int reamainingCooldown ;
    public warrior(String name, int hitPoints, int attack, int defense, int cooldown, BoardHelper boardHelper, MessageCallback callback) {
        super(name, hitPoints, attack, defense, boardHelper, callback);
        this.reamainingCooldown=0;
        this.WARRIOR_COOLDOWN = cooldown;
    }
    protected int healthGain(){
        return (HEALTH_GAIN+BONUS_HEALTH_WARRIOR) * level;
    }

    protected int attackGain(){
        return (ATTACK_GAIN+BONUS_ATTACK_WARRIOR) * level;
    }

    protected int defenseGain(){
        return (DEFENSE_GAIN+BONUS_DEFENSE_WARRIOR) * level;
    }
    @Override
    public void SpecialAbility() {
        if(reamainingCooldown > 0){
            callBack.send("trying to use special ability too soon");
        } else {
            health.gainHealth(defense * 10);
            reamainingCooldown = WARRIOR_COOLDOWN;
            hitEnemyInRange();
        }
    }



    @Override
    public void updateDelay() {
        reamainingCooldown = Math.min(reamainingCooldown - 1, 0);
    }

    public void hitEnemyInRange(){
        List<Enemy> potentialTargets = getPotentialTargets(3);
        if(!potentialTargets.isEmpty())
            hitRandomTarget(potentialTargets, health.getCurrent() / 10);
    }
    public void hitRandomTarget(List<Enemy> potentialTargets,int damage){
        Random rand = new Random();
        int generate = rand.nextInt(0, potentialTargets.size());
        Enemy target = potentialTargets.get(generate);
        target.takeDamage(damage,this);
    }

    public void levelup(){
        super.levelUp();
        reamainingCooldown = 0;
    }

}
