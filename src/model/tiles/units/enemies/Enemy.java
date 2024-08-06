package model.tiles.units.enemies;

import control.BoardGame;
import model.tiles.Empty;
import model.tiles.units.Unit;
import model.tiles.units.players.Player;
import model.utils.BoardHelper;
import model.utils.Position;
import model.utils.callbacks.MessageCallback;

import java.util.Random;

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
    }
    public void kill(Unit unit){
        unit.onDeath(this);
    }
    public void onDeath(Player p){
        callBack.send(this.getName() + " has died, xp gained is:" + experienceValue +'\n');
        p.addExperience(experienceValue);
        boardHelper.removeEnemy(this);
    }
    public void onDeath(Enemy e){
        return;//no friendly fire
    }
    public abstract Enemy getCopy();
    public void chasePlayer(Position posPlayer){
        int x= position.getX()-posPlayer.getX();
        int y= position.getY()-posPlayer.getY();
        int AbsX=Math.abs(x);
        int AbsY=Math.abs(y);
        if(AbsX>AbsY){
            if(x>0){
                move(position.getX()-1,position.getY());
            }
            else {
                move(position.getX()+1,position.getY());
            }
        }
        else {
            if(y>0){
                move(position.getX(),position.getY()-1);
            }
            else {
                move(position.getX(),position.getY()+1);
            }
        }
    }
    public void randomStep(){
        Random rand =new Random();
        int step= rand.nextInt(0,5);
        if(step==0){
            move(position.getX(),position.getY()+1);
        }
        else if(step==1){
            move(position.getX(),position.getY()-1);
        }
        else if(step==2){
            move(position.getX()+1,position.getY());
        }
        else if(step==3){
            move(position.getX()-1,position.getY());
        } else
            return;//do nothing
    }
}
