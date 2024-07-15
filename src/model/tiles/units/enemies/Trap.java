package model.tiles.units.enemies;

import control.BoardGame;
import model.utils.BoardHelper;
import model.utils.callbacks.MessageCallback;

public class Trap extends Enemy{
    final int VISIBILT_TIME;
    final int INVISIBILITY_TIME;
    int tickCount;
    boolean visible;
    public Trap(int visibilityTime, int invisibilityTime, char title, String name, int hitPoints, int attack, int defense, int experienceValue, BoardHelper boardHelper, MessageCallback callback){
        super(title, name, hitPoints, attack, defense, experienceValue, boardHelper, callback);
        VISIBILT_TIME = visibilityTime;
        INVISIBILITY_TIME = invisibilityTime;
        tickCount = 0;
        visible = true;
    }

    @Override
    public void SpecialAbility() {

    }

    public void OnTick(){
        visible = tickCount < VISIBILT_TIME;
        if(tickCount == VISIBILT_TIME + INVISIBILITY_TIME)
            tickCount = 0;
        else
            tickCount++;
        if(position.range(boardHelper.getPlayerPosition()) < 2)
            attackOther(boardHelper.getPlayer(), attack());
    }
    public char view(){
        if(visible)
            return this.tile;
        return '.';
    }
    public Trap(Trap other) {
        super(other.tile, other.name, other.health.getCapacity(), other.attack, other.defense, other.experienceValue, other.boardHelper, other.callBack);
        this.VISIBILT_TIME = other.VISIBILT_TIME;
        this.INVISIBILITY_TIME = other.INVISIBILITY_TIME;
        tickCount = 0;
        visible = true;
    }
}
