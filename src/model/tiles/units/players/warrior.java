package model.tiles.units.players;

import control.BoardGame;
import model.tiles.units.enemies.Enemy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class warrior extends  Player {
    protected  final int BONUS_HEALTH_WARRIOR = 5;
    protected final int BONUS_DEFENSE_WARRIOR = 1;
    protected final int BONUS_ATTACK_WARRIOR = 2;
    protected final int WARRIOR_COOLDOWN;
    protected int reamainingCooldown ;
    public warrior(String name, int hitPoints, int attack, int defense, BoardGame board, int cooldown) {
        super(name, hitPoints, attack, defense, board);
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
            //TODO: Error message
        } else {
            health.gainHealth(defense * 10);
            reamainingCooldown = WARRIOR_COOLDOWN;
            hitEnemyInRange();
        }
    }
    public void hitEnemyInRange(){
        List<Enemy> potentialTargets = getPotentialTargets();
        hitRandomTarget(potentialTargets);
    }

    public List<Enemy> getPotentialTargets(){
        List<Enemy> result = new LinkedList<Enemy>();
        for(Enemy enemy: board.getEnemies()){
            if(position.range(enemy.getPosition()) < 3)
                result.add(enemy);
        }
        return result;
    }
    public void hitRandomTarget(List<Enemy> potentialTargets){
        Random rand = new Random();
        int generate = rand.nextInt(0, potentialTargets.size());
        Enemy target = potentialTargets.get(generate);
        if(target != null) {
            target.takeDamage(health.getCurrent());
            if(!target.alive()){
                target.onDeath(this);

            }
        }
    }
    public void levelup(){
        super.levelUp();
        reamainingCooldown = 0;
    }

}
