package model.tiles.units.players;

import control.BoardGame;
import model.tiles.units.enemies.Enemy;

import java.util.List;
import java.util.Random;

public class Mage extends Player{
    int manaPool;
    final int MANA_COST;
    int currentMana;
    int spellPower;
    final int HITS_COUNT;
    final int ABILITY_RANGE;
    public Mage(String name, int hitPoints, int attack, int defense, BoardGame board,int manaPool, int manaCost, int spellPower, int hitsCount, int range){
        super(name, hitPoints, attack, defense, board);
        this.manaPool = manaPool;
        this.MANA_COST = manaCost;
        this.spellPower = spellPower;
        this.HITS_COUNT = hitsCount;
        this.ABILITY_RANGE = range;
        this.currentMana = this.manaPool / 4;
    }

    public void levelUp(){
        super.levelUp();
        manaPool += level * 25;
        currentMana = Math.min(manaPool, currentMana + manaPool / 4);
        spellPower += level * 10;
    }

    public void SpecialAbility(){
        if(currentMana > MANA_COST){
            //TODO: ERROR MESSAGE
        }  else {
            int hits = 0;
            List<Enemy> potentialTargets = getPotentialTargets(ABILITY_RANGE);
            int enemyNum = potentialTargets.size();
            while(hits < HITS_COUNT & enemyNum > 0){
                tryToHitRandomTargets(potentialTargets, spellPower);
                hits++;
                enemyNum = getPotentialTargets(ABILITY_RANGE).size();//ask roey
            }
            currentMana -= MANA_COST;
        }
    }
    public void tryToHitRandomTargets(List<Enemy> potentialTargets,int damage){
        Random rand = new Random();
        int generate = rand.nextInt(0, potentialTargets.size());
        Enemy target = potentialTargets.get(generate);
        if (target != null) {
            int defense = target.defend();
            target.takeDamage(Math.min(damage - defense ,0),this);
        }
    }

}
