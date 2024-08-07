package model.tiles.units.players;

import control.BoardGame;
import model.tiles.units.enemies.Enemy;
import model.utils.BoardHelper;
import model.utils.callbacks.MessageCallback;

import java.util.LinkedList;
import java.util.List;

public class warrior extends  Player {
    protected  final int BONUS_HEALTH_WARRIOR = 5;
    protected final int BONUS_DEFENSE_WARRIOR = 1;
    protected final int BONUS_ATTACK_WARRIOR = 2;
    protected final int WARRIOR_COOLDOWN;
    protected int reamainingCooldown;
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
            callBack.send("trying to use special ability too soon : wait for " + reamainingCooldown + " rounds\n");
        } else {
            callBack.send(this.getName() + " used Avenger's shield, healing for " + defense * 10 + "\n");
            health.gainHealth(defense * 10);
            reamainingCooldown = WARRIOR_COOLDOWN;
            hitEnemyInRange();
        }
    }



    @Override
    public void updateDelay() {
        reamainingCooldown = Math.max(reamainingCooldown - 1, 0);
    }

    public void hitEnemyInRange(){
        List<Enemy> potentialTargets = getPotentialTargets(3);
        if(!potentialTargets.isEmpty())
            hitRandomTarget(potentialTargets, health.getCurrent() / 10);
    }
    public void hitRandomTarget(List<Enemy> potentialTargets,int damage){
        int generate = generator.generate(potentialTargets.size());
        Enemy target = potentialTargets.get(generate);
        attackOtherAbility(target, damage);
    }

    public void levelUp(){
        super.levelUp();
        reamainingCooldown = 0;
    }

    public String toString(){
        return super.toString() + " ,cooldown: " + reamainingCooldown + "/" + WARRIOR_COOLDOWN;
    }

}
