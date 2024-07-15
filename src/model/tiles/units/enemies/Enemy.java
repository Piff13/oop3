package model.tiles.units.enemies;

import control.BoardGame;
import model.tiles.Empty;
import model.tiles.units.Unit;
import model.tiles.units.players.Player;
import model.utils.BoardHelper;
import model.utils.Position;
import model.utils.callbacks.MessageCallback;

public abstract class Enemy extends Unit {
    protected int experienceValue;

    public Enemy(char tile, String name, int hitPoints, int attack, int defense, int experienceValue, BoardHelper boardHelper, MessageCallback callback) {
        super(tile, name, hitPoints, attack, defense, boardHelper, callback);
        this.experienceValue = experienceValue;
    }

    public int experienceValue() {
        return experienceValue;
    }


    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void visit(Enemy e){
        // Do nothing
    }

    public void visit(Player p) {
        combatBattle(p);
        if (!p.alive()){
            p.onDeath(this);
        }
    }
    public void kill(Unit unit){
        unit.onDeath(this);
    }
    public void onDeath(Player p){
        p.addExperience(experienceValue);
        boardHelper.removeEnemy(this);
        callBack.send(this.toString() + "has died, xp gained is:" + experienceValue);
    }
    public void onDeath(Enemy e){
        return;//no friendly fire
    }

}
