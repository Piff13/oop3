package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;
import model.utils.BoardHelper;
import model.utils.callbacks.MessageCallback;

import java.util.List;

public class Hunter extends Player{
    public int range;
    public int arrowCount;
    public int ticksCount = 0;
    private int tickForMoreArrows = 10;
    protected final int DEFENSE_GAIN_HUNTER = 1;
    protected final int ATTACK_GAIN_HUNTER = 2;
    public Hunter(String name, int hitPoints, int attack, int defense, BoardHelper boardHelper, MessageCallback callback, int range){
        super(name, hitPoints, attack, defense, boardHelper, callback);
        this.range = range;
        this.arrowCount = level * 10;
    }

    protected int attackGain(){
        return (ATTACK_GAIN+ATTACK_GAIN_HUNTER) * level;
    }

    protected int defenseGain(){
        return (DEFENSE_GAIN+DEFENSE_GAIN_HUNTER) * level;
    }
    public void levelUp(){
        super.levelUp();
        arrowCount += level * 10;
        attack += level * 2;
        defense += level;
    }
    public void updateDelay(){
        if(ticksCount == tickForMoreArrows) {
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
        if(arrowCount == 0)
            callBack.send("no more arrows\n");
        else if(target == null){
            callBack.send(this.getName() + " tried to use Shoot, but there are no foes in range\n");
        }
        else {
            callBack.send(this.getName() + " fired an arrow at " + target.getName() + "\n");
            arrowCount--;
            attackOtherAbility(target, attack);
        }
    }

    public String toString() {
        return super.toString() + " ,arrowCount: " + arrowCount + " ,tickCount: " + ticksCount + "/" + tickForMoreArrows;
    }
}
