package model.tiles.units.enemies;

import model.tiles.units.HeroicUnit;
import model.tiles.units.Unit;
import model.utils.BoardHelper;
import model.utils.Position;
import model.utils.callbacks.MessageCallback;

public class Boss extends Monster implements HeroicUnit {
    protected int abilityFrequency;
    protected int combatTicks = 0;
    public Boss(char tile, String name, int hitPoints, int attack, int defense, int experienceValue, int vision, BoardHelper boardHelper, MessageCallback callback, int abilityFrequency){
        super(tile, name, hitPoints, attack, defense, experienceValue, vision, boardHelper, callback);
        this.abilityFrequency = abilityFrequency;
    }
    public Boss(Boss other){
        super(other.tile, other.name, other.health.getCapacity(),other.attack, other.defense, other.experienceValue, other.vision, other.boardHelper, other.callBack);
        this.abilityFrequency = other.abilityFrequency;
    }
    public void attackOtherAbility(Unit target, int damage){
        int defense = target.defend();
        callBack.send(target.getName() + " has rolled " + defense + " defense points\n");
        int damageTaken = Math.max(damage - defense, 0);
        callBack.send((this.getName() + " hit " + target.getName() + " for " +  damageTaken + " ability damage\n"));
        target.takeDamage(damageTaken,this);
    }
    public void castAbility(){
        attackOtherAbility(boardHelper.getPlayer(), attack);
    }
    public void OnTick(){
        Position pos = boardHelper.getPlayerPosition();
        if(isPlayerInVision(pos)){
            if(combatTicks == abilityFrequency){
                combatTicks = 0;
                castAbility();
            } else {
                combatTicks++;
                chasePlayer(pos);
            }
        } else {
            combatTicks = 0;
            randomStep();
        }
    }
    public String toString(){
        return super.toString() + " ,combatTick: " + combatTicks + "/" + abilityFrequency;
    }
    public Enemy getCopy(){
        return new Boss(this);
    }
}
