package model.tiles.units.players;

import control.BoardGame;
import model.tiles.units.enemies.Enemy;
import model.utils.BoardHelper;
import model.utils.callbacks.MessageCallback;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.System.in;

public class Rogue extends Player{

    protected final int cost;
    protected int energy;
    protected final int BONUS_ATTACK_WARRIOR = 3;
    protected  final  int MAX_ENENERGY =100;

    public Rogue(String name, int hitPoints, int attack, int defense, int cost, BoardHelper boardHelper, MessageCallback callback) {
        super(name, hitPoints, attack, defense, boardHelper, callback);
        this.cost = cost;
        this.energy=100;
    }
    protected int attackGain(){
        return (ATTACK_GAIN+BONUS_ATTACK_WARRIOR) * level;
    }
    public void levelup(){
        super.levelUp();
        energy = MAX_ENENERGY;
    }


    @Override
    public void SpecialAbility() {
        if(energy-cost < 0){
            callBack.send("trying to use special ability too soon\n");
        } else {
            callBack.send(this.getName() + " used Fan of Knives\n");
            energy -= cost;
            hitEnemyInRange();
        }
    }



    public void updateDelay() {
        energy = Math.min(energy + 10, MAX_ENENERGY);
    }

    public void hitEnemyInRange(){
        List<Enemy> potentialTargets = getPotentialTargets(2);
        hitTargets(potentialTargets);
    }

    public void hitTargets(List<Enemy> potentialTargets){
        for (Enemy enemy : potentialTargets) {
            attackOtherAbility(enemy, attack);
        }
    }

    public String toString(){
        return super.toString() + ", energy: " + energy + "/" + MAX_ENENERGY;
    }

}
