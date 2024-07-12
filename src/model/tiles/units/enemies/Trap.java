package model.tiles.units.enemies;

import control.BoardGame;

public class Trap extends Enemy{
    final int VISIBILT_TIME;
    final int INVISIBILITY_TIME;
    int tickCount;
    boolean visible;
    public Trap(int visibilityTime, int invisibilityTime,char title, String name, int hitPoints, int attack, int defense, int experienceValue, BoardGame board){
        super(title, name, hitPoints, attack, defense, experienceValue, board);
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
        if(position.range(board.player.getPosition()) < 2)
            attackOther(board.player,attack());
    }
}
