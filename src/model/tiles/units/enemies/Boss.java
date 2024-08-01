package model.tiles.units.enemies;

import model.tiles.units.HeroicUnit;
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
    public void castAbility(){
        attackOther(boardHelper.getPlayer(), attack);
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
}
