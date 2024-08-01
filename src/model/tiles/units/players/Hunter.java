package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;
import model.utils.BoardHelper;
import model.utils.callbacks.MessageCallback;

import java.util.List;

public class Hunter extends Player{
    public int range;
    public int arrowCount;
    public int ticksCount = 0;
    public Hunter(String name, int hitPoints, int attack, int defense, BoardHelper boardHelper, MessageCallback callback, int range){
        super(name, hitPoints, attack, defense, boardHelper, callback);
        this.range = range;
        this.arrowCount = level * 10;
    }
    public void levelUp(){
        super.levelUp();
        arrowCount += level * 10;
        attack += level * 2;
        defense += level;
    }
    public void updateDelay(){
        if(ticksCount == 10) {
            arrowCount += level;
            ticksCount = 0;
        } else {
            ticksCount++;
        }
    }
    public Enemy getClosestEnemyInRange(){
        List<Enemy> potentialTargets = getPotentialTargets(range);
        double minRange = range + 1;//initial value to be overrided
        Enemy minTarget = null;
        for(Enemy target : potentialTargets){
            double rangeFromPlayer = target.getPosition().range(boardHelper.getPlayerPosition());
            if(rangeFromPlayer < minRange){
                minTarget = target;
                minRange = rangeFromPlayer;
            }
        }
        return minTarget;
    }
    public void SpecialAbility(){
        Enemy target = getClosestEnemyInRange();
        if(target == null || arrowCount == 0)
            callBack.send("unable to cast ability due to lack of arrows/ potential targets\n");
        else {
            arrowCount--;
            attackOther(target, attack);
        }
    }

    public String toString() {
        return super.toString() + " ,arrowCount: " + arrowCount + " ,tickCount: " + ticksCount;
    }
}
