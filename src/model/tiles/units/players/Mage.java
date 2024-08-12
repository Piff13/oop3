package model.tiles.units.players;

import control.BoardGame;
import model.tiles.units.enemies.Enemy;
import model.utils.BoardHelper;
import model.utils.callbacks.MessageCallback;

import java.util.List;

public class Mage extends Player{
    protected int manaPool;
    protected final int MANA_COST;
    protected int currentMana;
    protected int spellPower;
    protected final int HITS_COUNT;
    protected final int ABILITY_RANGE;
    public Mage(String name, int hitPoints, int attack, int defense, int manaPool, int manaCost, int spellPower, int hitsCount, int range, BoardHelper boardHelper, MessageCallback callback){
        super(name, hitPoints, attack, defense, boardHelper, callback);
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
    protected String printLevelUpStats(){
        String str = super.printLevelUpStats();
        str += level * 25 + " manaPool " + level * 10 + " spellPower";
        return str;
    }

    public void SpecialAbility(){
        if(currentMana < MANA_COST){
            callBack.send("trying to use special ability too soon: " + currentMana + "/" + MANA_COST+"\n");
        }  else {
            callBack.send(this.getName() + " used Blizzard\n");
            int hits = 0;
            List<Enemy> potentialTargets = getPotentialTargets(ABILITY_RANGE);
            int enemyNum = potentialTargets.size();
            while(hits < HITS_COUNT & enemyNum > 0){
                tryToHitRandomTargets(potentialTargets, spellPower);
                hits++;
                enemyNum = potentialTargets.size();//since there is no movement during ability, we can use the same list
            }
            currentMana -= MANA_COST;
        }
    }



    public void updateDelay() {
        currentMana = Math.min(currentMana + level, manaPool);
    }


    public void tryToHitRandomTargets(List<Enemy> potentialTargets,int damage){
        int generate = generator.generate(potentialTargets.size());
        Enemy target = potentialTargets.get(generate);
        attackOtherAbility(target, damage);
        if(!target.alive())
            potentialTargets.remove(target);
    }

    public String toString(){
        return super.toString() + " ,mana: " + currentMana + "/" + manaPool + " ,spellPower: " + spellPower;
    }

}
