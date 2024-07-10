package model.tiles.units.players;

import control.BoardGame;
import model.tiles.units.enemies.Enemy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.System.in;

public class Rogue extends Player{

    protected final int cost;
    protected  int energy;
    protected final int BONUS_ATTACK_WARRIOR = 3;

    public Rogue(String name, int hitPoints, int attack, int defense, BoardGame board, int cost) {
        super(name, hitPoints, attack, defense, board);
        this.cost = cost;
        this.energy=100;
    }
    protected int attackGain(){
        return (ATTACK_GAIN+BONUS_ATTACK_WARRIOR) * level;
    }
    public void levelup(){
        super.levelUp();
        energy = 100;
    }




    @Override
    public void SpecialAbility() {
        if(energy-cost < 0){
            //TODO: Error message
        } else {
            energy -= cost;
            hitEnemyInRange();
        }
    }
    public void hitEnemyInRange(){
        List<Enemy> potentialTargets = getPotentialTargets(2);
        hitRandomTarget(potentialTargets);
    }


    public void hitRandomTarget(List<Enemy> potentialTargets){
        for (Enemy enemy : potentialTargets) {
            int defence= enemy.defend();
            int reduce=attack-defence;
            int minReduce=Math.min(reduce,0);
            enemy.takeDamage(minReduce);
        }
    }
}
